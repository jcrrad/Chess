package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import controller.GameWindowController;

public class GameView extends View{
	private static final long serialVersionUID = 1L;
	BoardPanel board;
	TabbedPanel chat;
	ButtonPanel buttonPanel;
	
	// TODO add clock Section
	public GameView(ChessFrame frame) {
		super(frame);

		buttonPanel = new ButtonPanel();
		chat = new TabbedPanel();
		board = new BoardPanel();
		setLayout(new GridBagLayout());
		
		initBoard();
		initChat();
		initButtonPanel();
	}
	
	private void initBoard()
	{
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.weighty = 4.0;
		gbc_board.weightx = 1;
		this.add(board, gbc_board);
	}
	
	private void initChat()
	{
		GridBagConstraints gbc_chat = new GridBagConstraints();
		gbc_chat.fill = GridBagConstraints.BOTH;
		gbc_chat.weighty = 4.0;
		gbc_chat.weightx = 10;
		gbc_chat.gridwidth = GridBagConstraints.REMAINDER;
		this.add(chat, gbc_chat);
	}
	
	private void initButtonPanel()
	{
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridwidth = GridBagConstraints.REMAINDER;
		gbc_buttonPanel.weighty = 1.0;
		this.add(buttonPanel, gbc_buttonPanel);
	}
	
	public void setBoardPieceListener(ActionListener listener)
	{
		this.board.setPieceListener(listener);
	}
	
	public void setButtonPanelQuitListener(ActionListener listener)
	{
		this.buttonPanel.quit.addActionListener(listener);
	}
	
	public void setButtonPanelStalemateListener(ActionListener listener)
	{
		this.buttonPanel.stalemate.addActionListener(listener);
	}
	
	public void setChatPanelSubmitListener(ActionListener listener)
	{
		this.chat.chatSection.submitButton.addActionListener(listener);
	}
	
	public String getChatPanelInputField()
	{
		return this.chat.chatSection.inputField.getText();
	}
}
