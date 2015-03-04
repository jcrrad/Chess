package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;

public class GameView extends View {
	private static final long serialVersionUID = 1L;
	BoardPanel board;
	TabbedPanel chat;
	ButtonPanel buttonPanel;

	public GameView(ChessFrame frame) {
		super(frame);
		setBackground(Color.BLACK);

		chat = new TabbedPanel();
		board = new BoardPanel();
		board.setBackground(Color.BLACK);
		GridLayout gridLayout_1 = (GridLayout) board.getLayout();
		gridLayout_1.setVgap(1);
		gridLayout_1.setHgap(1);
		buttonPanel = new ButtonPanel();
		GridLayout gridLayout = (GridLayout) buttonPanel.getLayout();
		gridLayout.setColumns(3);
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);

		initBoard();
		initChat();
		initButtonPanel();
	}

	private void initBoard() {
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.anchor = GridBagConstraints.NORTHWEST;
		gbc_board.weightx = 0.9;
		gbc_board.weighty = 0.9;
		gbc_board.fill = GridBagConstraints.BOTH;
		this.add(board, gbc_board);
	}

	private void initChat() {
		GridBagConstraints gbc_chat = new GridBagConstraints();
		gbc_chat.anchor = GridBagConstraints.NORTHEAST;
		gbc_chat.weighty = 0.9;
		gbc_chat.fill = GridBagConstraints.VERTICAL;
		gbc_chat.gridwidth = GridBagConstraints.REMAINDER;
		this.add(chat, gbc_chat);
	}

	private void initButtonPanel() {
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.anchor = GridBagConstraints.SOUTH;
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridwidth = GridBagConstraints.REMAINDER;

		gbc_buttonPanel.weighty = 0.1;
		this.add(buttonPanel, gbc_buttonPanel);
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
		this.chat.chatSection.submitButton.addActionListener(listener);
	}

	public String getChatPanelInputField() {
		String text = this.chat.chatSection.inputField.getText();
		this.chat.chatSection.inputField.setText("");
		return text;
	}

	public void updateChat(String text) {
		this.chat.chatSection.conversationField
				.setText(this.chat.chatSection.conversationField.getText()
						+ "\r\n" + text);
	}
}