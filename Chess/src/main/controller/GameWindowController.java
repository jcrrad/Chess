package controller;

import gui.GameView;
import gui.Square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.client.Coordinate;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;

public class GameWindowController implements Observer{

	private Model model;
	private GameView view;
	private Coordinate start, end;
	private Date time;
	private boolean attempt, check;

	public GameWindowController(Model model, GameView view) {
		this.model = model;
		this.view = view;
		model.registerObserver(this);
		
		view.setButtonPanelQuitListener(new ButtonPanelQuitListener());
		view.setChatPanelSubmitListener(new ChatPanelSubmitListener());
		view.setButtonPanelStalemateListener(new ButtonPanelStalemateListener());
		view.setBoardPieceListener(new BoardPieceListener());
	}

	public void sendMessage(String text) {  
		/*
		 * TODO: This will be the method to send messages out to the server,
		 * this controller will also receive messages, we should keep the null
		 * check as this will keep us from sending blank messages back and forth
		 * and wasting resources
		 */
		if (text.length() != 0)
			System.out.println(text);
		// model.sendMessage();
	}

	public void handleQuit() {
		/*
		 * TODO: This will be the method called when a player quits, the player
		 * who does will be returned to the login screen, but the opposing
		 * player needs to be alerted.
		 */
		System.out.println("Quit Requested");
	}

	public void offerStalemate() {
		// TODO: This will be called when one person offers a stalemate
		System.out.println("Stalemate Offered");
	}

	public void killWindow() {
		//view.dispose();
	}

	@Override
	public void update() 
	{
		System.out.println("Checking ingame");
		if(model.getState() == STATE.INGAME)
		{
			System.out.println("INGAME");
			view.update();
		}
	}
	
	@Override
	public void update(Object message)
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
		}
	}
	
	class ButtonPanelStalemateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Offering Stalemate");
		}
	}
	
	class BoardPieceListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(start == null)
			{
				Square square = (Square) arg0.getSource();
				start.setX(square.getColumn());
				start.setY(square.getRow());
			}
			else
			{
				Square square = (Square) arg0.getSource();
				end.setX(square.getColumn());
				end.setY(square.getRow());
				if(!((start.getX() == end.getX()) && (start.getY() == end.getY())))
				{
					attempt = model.tryPlayerMove(start, end);
					check = model.isInCheckmate();
					if(attempt && !check)
					{
						view.update();
					}
				}
			}
			System.out.println("A board piece moved");
		}
		
	}
}
