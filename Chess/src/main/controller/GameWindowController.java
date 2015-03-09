package controller;

import gui.GameView;
import gui.Square;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.client.Connection;
import core.client.Coordinate;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;
import core.client.game.Board;
import core.client.game.Piece;

public class GameWindowController implements Observer {

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

	protected void updateBoardUI(Board board) {
		Coordinate coords = new Coordinate();
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j) {
				coords.setX(j);
				coords.setY(i);

				Piece p = board.getPiece(coords);
				view.setPiece(p.getName(), j, i, p.getColor());
			}
	}

	@Override
	public void update() {
		if (model.getState() == STATE.INGAME) {
			updateBoardUI(model.getBoard());
			view.update();

			if (model.isStartingPlayer())
				view.unlockBoard();
		}
	}

	@Override
	public void update(Object message) {
		Message mes = (Message) message;
		if (mes.isStalemate()) {
			updateStalemate(message);
		} else if (mes.hasBoardUpdate()) {
			updateBoard(message);
		} else if (mes.hasChat()) {
			updateChat(message);
		}
	}

	public void updateStalemate(Object message) {
		view.lockBoard();
		// Lock the board and offer a stalemate on the gui
		view.update();
		view.unlockBoard();
	}

	public void updateBoard(Object message) {
		Message mes = (Message) message;
		String boardRep = mes.getBoard();
		Board board = new Board(boardRep);
		updateBoardUI(board);
		model.setBoard(board);
		view.unlockBoard();
		view.update();
	}

	public void updateChat(Object message) {
		String username = "opponent";
		Message chatMessage = (Message) message;
		time = new Date();
		String timeString = new SimpleDateFormat("HH:mm:ss").format(time);

		if (!chatMessage.getUsername().equals("")
				&& chatMessage.getUsername() != null)
			username = chatMessage.getUsername();

		String chat = timeString + " " + username + ": "
				+ chatMessage.getChatText();
		view.updateChat(chat);
	}

	class ButtonPanelQuitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState(STATE.QUIT);
			// TODO: Add forfeit message to send
			view.quit();
		}
	}

	class ChatPanelSubmitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Connection connection = model.getConnection();
			if (connection != null) {
				Message message = new Message();
				String timeString = new SimpleDateFormat("HH:mm:ss")
						.format(time);
				message.setChatText(view.getChatPanelInputField());
				message.setUsername(model.getUsername());
				connection.send(message);
				view.updateChat(timeString + " You: " + message.getChatText());
			}
		}
	}

	class ButtonPanelStalemateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Connection connection = model.getConnection();
			if (connection != null) {
				Message message = new Message();
				message.setStalemate(true);
				connection.send(message);
			}
		}
	}

	// Need to add a check for checkmate
	class BoardPieceListener implements ActionListener {
		private boolean attempt;
		private Coordinate start;

		@Override
		public void actionPerformed(ActionEvent e) {
			Square square = (Square) e.getSource();

			if (isBlankOrOpponentPiece(square) && start == null)
				return;

			Coordinate location = new Coordinate();
			location.setX(square.getColumn());
			location.setY(square.getRow());

			if (start == null) {
				recordPickUp(location);
			} else {
				processMove(start, location);
				start = null;
				view.update();
			}
		}

		private boolean isBlankOrOpponentPiece(Square s) {
			return (s.getText().equals("") || !model.getColor().equals(
					s.getColor()));
		}

		private void recordPickUp(Coordinate location) {
			start = location;
		}

		private void processMove(Coordinate start, Coordinate end) {
			view.lockBoard();

			attempt = model.tryPlayerMove(start, end);
			System.out.println(attempt);
			if (attempt) {
				updateBoardUI(model.getBoard());
				sendBoardMessage();
				return;
			}
			view.unlockBoard();
			return;
		}

		private void sendBoardMessage() {
			Connection connection = model.getConnection();
			if (connection != null) {
				Message message = new Message();
				message.setBoard(model.getBoard().toString());
				connection.send(message);
			}
		}
	}
}
