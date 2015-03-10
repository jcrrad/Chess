package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class GameView extends View {
	private static final long serialVersionUID = 1L;
	BoardPanel board;
	ChatPanel chat;
	ButtonPanel buttonPanel;
	private JLabel label;

	// TODO add clock Section
	public GameView(ChessFrame frame) {
		super(frame);

		initBoard();
		initChat();
		initButtonPanel();

		lockBoard();
	}

	private void initBoard() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 566, 100 };
		gridBagLayout.rowHeights = new int[] { 700, 50 };
		gridBagLayout.columnWeights = new double[] { 0.1, 0.9 };
		gridBagLayout.rowWeights = new double[] { 0.9, 0.1 };
		setLayout(gridBagLayout);
		board = new BoardPanel();
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.insets = new Insets(0, 0, 5, 5);
		gbc_board.gridx = 0;
		gbc_board.gridy = 0;
		this.add(board, gbc_board);
		chat = new ChatPanel();
		GridBagConstraints gbc_chat = new GridBagConstraints();
		gbc_chat.fill = GridBagConstraints.BOTH;
		gbc_chat.insets = new Insets(0, 0, 5, 0);
		gbc_chat.gridx = 1;
		gbc_chat.gridy = 0;
		this.add(chat, gbc_chat);

		label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		add(label, gbc_label);

		buttonPanel = new ButtonPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 1;
		gbc_buttonPanel.gridy = 1;
		this.add(buttonPanel, gbc_buttonPanel);
	}

	private void initChat() {
	}

	private void initButtonPanel() {
	}

	public void setBoardPieceListener(ActionListener listener) {
		this.board.setPieceListener(listener);
	}

	public void setButtonPanelQuitListener(ActionListener listener) {
		this.buttonPanel.quit.addActionListener(listener);
	}

	public void setButtonPanelStalemateListener(ActionListener listener) {
		this.buttonPanel.stalemate.addActionListener(listener);
	}

	public void setChatPanelSubmitListener(ActionListener listener) {
		this.chat.submitButton.addActionListener(listener);
	}

	public String getChatPanelInputField() {
		String text = this.chat.inputField.getText();
		this.chat.inputField.setText("");
		return text;
	}

	public void updateChat(String text) {
		this.chat.conversationField.setText(this.chat.conversationField
				.getText() + "\r\n" + text);
	}

	public void setPiece(String name, int x, int y, Color color) {
		board.squares[x][y].setText(name);
		board.squares[x][y].setForeground(color);
		board.squares[x][y].color = color;
	}

	public void lockBoard() {
		Component[] comps = board.getComponents();
		for (Component c : comps) {
			c.setEnabled(false);
		}
	}

	public void unlockBoard() {
		Component[] comps = board.getComponents();
		for (Component c : comps) {
			c.setEnabled(true);
		}
	}

	public void clearChat() {
		this.chat.conversationField.setText("");
	}
}
