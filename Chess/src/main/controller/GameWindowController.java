package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gui.GameView;
import gui.Square;

import java.text.SimpleDateFormat;
import java.util.Date;

import core.client.game.Board;
import core.client.game.Piece;
import core.client.Connection;
import core.client.Coordinate;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;

public class GameWindowController implements Observer{

	private Model model;
	private GameView view;
	private Date time;

	public GameWindowController(Model model, GameView view) {
		this.model = model;
		this.view = view;
		model.registerObserver(this);
		
		view.setButtonPanelQuitListener(new ButtonPanelQuitListener());
		view.setChatPanelSubmitListener(new ChatPanelSubmitListener());
		view.setButtonPanelStalemateListener(new ButtonPanelStalemateListener());
		view.setBoardPieceListener(new BoardPieceListener());
	}
	
	protected void updateBoardUI(Board board)
	{
		Coordinate coords = new Coordinate();
		for(int i = 0; i < 8; ++i)
			for(int j = 0; j < 8; ++j)
			{
				coords.setX(j);
				coords.setY(i);
				
				Piece p = board.getPiece(coords);
				System.out.println(p.getName());
				view.setPiece(p.getName(), j, i, p.getColor());
			}
	}

	@Override
	public void update() 
	{
		System.out.println("Checking ingame");
		if(model.getState() == STATE.INGAME)
		{
			updateBoardUI(model.getBoard());
			System.out.println("INGAME");
			view.update();
		}
	}
	
	@Override
	public void update(Object message)
	{
		Message mes = (Message) message;
		if(mes.isStalemate())
		{
			updateStalemate(message);
		}
		else if(mes.hasBoardUpdate())
		{
			updateBoard(message);
		}
		else if(mes.hasChat())
		{
			updateChat(message);
		}
	}
	
	public void updateStalemate(Object message)
	{
		view.lockBoard();
		//Lock the board and offer a stalemate on the gui
		view.update();
		view.unlockBoard();
	}
	
	public void updateBoard(Object message)
	{
		Message mes = (Message) message;
		String boardRep = mes.getBoard();
		Board board = new Board(boardRep);
		updateBoardUI(board);
		model.setBoard(board);
		view.unlockBoard();
		view.update();
	}
	
	public void updateChat(Object message)
	{
		Message chatMessage = (Message) message;
		time = new Date();
		String timeString = new SimpleDateFormat("HH:mm:ss").format(time);
		String chat = timeString+" "+model.getUsername()+": "+chatMessage.getChatText(); 
		view.updateChat(chat);
	}

	class ButtonPanelQuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			model.setState(STATE.QUIT);
			//TODO: Add forfeit message to send
			view.quit();
		}	
	}
	
	class ChatPanelSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Sending Text");
			Connection connection = model.getConnection();
			if(connection != null)
			{
				Message message = new Message();
				message.setChatText(view.getChatPanelInputField());
				connection.send(message);
				view.updateChat(message.getChatText());
			}
		}
	}
	
	class ButtonPanelStalemateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Offering Stalemate");
			Connection connection = model.getConnection();
			if(connection != null)
			{
				Message message = new Message();
				message.setStalemate(true);
				connection.send(message);
			}
		}
	}
	
	class BoardPieceListener implements ActionListener
	{
		private boolean attempt, check;
		private Coordinate start, end;
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Square square = (Square) e.getSource();
			
			if(square.getText().equals("") && start == null)
				return;
			
			Coordinate location = new Coordinate();
			location.setX(square.getColumn());
			location.setY(square.getRow());
			
			if(start == null)
			{
				recordPickUp(location);
			}
			else
			{
				view.lockBoard();
				recordPutDown(location);
				if(!isSamePosition())
				{
					model.lockBoard();
					Color playerColor = model.getPiece(start).getColor();
					attempt = model.tryPlayerMove(start, end);
					check = model.isInCheck(playerColor);
					if(attempt && !check)
					{
						System.out.println("good move");
						updateBoardUI(model.getBoard());
						view.update();
					}
					else
					{
						System.out.println("How did you get here?");
						//view.unlockBoard();
					}
				}
				start = null;
				end = null;
			}
			System.out.println("Action performed ended");
		}
		
		private void recordPickUp(Coordinate location)
		{
			start = location;
		}
		
		private void recordPutDown(Coordinate location) 
		{
			end = location;
		}
		
		private boolean isSamePosition()
		{
			return ((start.getX() == end.getX()) && (start.getY() == end.getY()));
		}
	}	
}
