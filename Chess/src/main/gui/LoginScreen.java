package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.GameWindowController;

public class LoginScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	View parentView;

	JTextField input = new JTextField();
	JLabel username = new JLabel("What is your username?");
	JLabel welcome = new JLabel("Welcome to Chess");
	JButton connectButton = new JButton("Connect");
	JButton about = new JButton("About");
	

	public LoginScreen(View parent, final GameWindowController gameWindowController) {
		parentView = parent;
		setLayout(new GridBagLayout());

		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setLabelFor(this);
		GridBagConstraints gbc_welcome = new GridBagConstraints();
		gbc_welcome.fill = GridBagConstraints.BOTH;
		gbc_welcome.insets = new Insets(0, 0, 5, 5);
		gbc_welcome.gridx = 1;
		gbc_welcome.gridy = 0;
		this.add(welcome, gbc_welcome);

		username.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.anchor = GridBagConstraints.SOUTH;
		gbc_username.fill = GridBagConstraints.BOTH;
		gbc_username.gridx = 1;
		gbc_username.gridy = 1;
		this.add(username, gbc_username);

		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.insets = new Insets(0, 0, 5, 5);
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.gridx = 1;
		gbc_input.gridy = 2;
		this.add(input, gbc_input);

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		add(connectButton, gbc_btnNewButton);

		GridBagConstraints gbc_about = new GridBagConstraints();
		gbc_about.insets = new Insets(0, 0, 0, 5);
		gbc_about.gridx = 0;
		gbc_about.gridy = 4;
		this.add(about, gbc_about);

		JButton quit = new JButton("Quit");
		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.insets = new Insets(0, 0, 0, 5);
		gbc_quit.gridx = 2;
		gbc_quit.gridy = 4;
		this.add(quit, gbc_quit);

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quit();
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aboutScreen();
			}
		});
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gameWindowController.connect();
			}
		});
	}

	public void quit() {
		parentView.kill();
	}

	public void aboutScreen() {
		parentView.goToAbout();
	}
}
