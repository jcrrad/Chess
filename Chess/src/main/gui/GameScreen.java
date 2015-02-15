package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

	BoardPanel board;
	TabbedPanel chat;
	ButtonPanel buttonPanel;
	View parentView;

	// TODO add clock Section
	public GameScreen(View parent) {
		super();
		parentView = parent;
		buttonPanel = new ButtonPanel(parent);
		chat = new TabbedPanel(parent);
		board = new BoardPanel(parent);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.weighty = 4.0;
		gbc_board.weightx = 1;
		this.add(board, gbc_board);
		GridBagConstraints gbc_chat = new GridBagConstraints();
		gbc_chat.fill = GridBagConstraints.BOTH;
		gbc_chat.weighty = 4.0;
		gbc_chat.weightx = 10;
		gbc_chat.gridwidth = GridBagConstraints.REMAINDER;
		this.add(chat, gbc_chat);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridwidth = GridBagConstraints.REMAINDER;

		gbc_buttonPanel.weighty = 1.0;
		this.add(buttonPanel, gbc_buttonPanel);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
