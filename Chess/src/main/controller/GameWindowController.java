package controller;

import gui.GameView;
import gui.Square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

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
		} else if (model.getState() == STATE.LOGIN) {
			model.clearBoard();
			view.clearChat();
		}
	}

	@Override
	public void update(Object message) {
		Message mes = (Message) message;
		Connection connection = model.getConnection();

		if (mes.isWinner()) {
			view.lockBoard();
			JOptionPane.showInputDialog(view, "You have Won.", "GameOver",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (mes.hasBoardUpdate()) {
			updateBoard(message);
		} else if (mes.hasChat()) {
			updateChat(message);
		}
		if (model.isInCheckmate(model.getColor())) {
			view.lockBoard();
			gameOver();
			JOptionPane.showMessageDialog(view, "You have Lost.", "GameOver",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	private void gameOver() {
		Connection connection = model.getConnection();
		if (connection != null) {
			Message message = new Message();
			// opponent is winner
			message.setWinner(true);
			connection.send(message);
		}
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
		String username = "Opponent";
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
				time = new Date();
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
			if (attempt) {
				updateBoardUI(model.getBoard());
				sendBoardMessage();
				return;
			} else if (model.isInCheckmate(model.getColor())) {
				view.lockBoard();
				gameOver();
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
