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

import controller.ConnectionController;

public class LoginView extends JPanel implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChessFrame frame;
	JTextField usernameText = new JTextField();
	JLabel usernameLabel = new JLabel("What is your username?");
	JLabel welcomeLabel = new JLabel("Welcome to Chess");
	JButton connectButton = new JButton("Connect");
	JButton aboutButton = new JButton("About");
	JButton quitButton = new JButton("Quit");
	

	public LoginView(ChessFrame frame) {
		this.frame = frame;
		this.frame.register(this);
		setLayout(new GridBagLayout());

		initWelcome();
		initUsername();
		initUsernameInput();
		initConnectButton();
		initAbout();
		initQuitButton();
	}

	private void initQuitButton() 
	{
		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.insets = new Insets(0, 0, 0, 5);
		gbc_quit.gridx = 2;
		gbc_quit.gridy = 4;
		this.add(quitButton, gbc_quit);
	}

	private void initAbout() 
	{
		GridBagConstraints gbc_about = new GridBagConstraints();
		gbc_about.insets = new Insets(0, 0, 0, 5);
		gbc_about.gridx = 0;
		gbc_about.gridy = 4;
		this.add(aboutButton, gbc_about);
	}

	private void initConnectButton() 
	{
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		add(connectButton, gbc_btnNewButton);

	}

	private void initUsernameInput() 
	{
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.insets = new Insets(0, 0, 5, 5);
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.gridx = 1;
		gbc_input.gridy = 2;
		this.add(usernameText, gbc_input);
	}

	private void initUsername() 
	{
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.anchor = GridBagConstraints.SOUTH;
		gbc_username.fill = GridBagConstraints.BOTH;
		gbc_username.gridx = 1;
		gbc_username.gridy = 1;
		this.add(usernameLabel, gbc_username);
	}

	private void initWelcome() 
	{
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setLabelFor(this);
		GridBagConstraints gbc_welcome = new GridBagConstraints();
		gbc_welcome.fill = GridBagConstraints.BOTH;
		gbc_welcome.insets = new Insets(0, 0, 5, 5);
		gbc_welcome.gridx = 1;
		gbc_welcome.gridy = 0;
		this.add(welcomeLabel, gbc_welcome);
	}
	
	public void setAboutListener(ActionListener listener)
	{
		this.aboutButton.addActionListener(listener);
	}
	
	public void setQuitListener(ActionListener listener)
	{
		this.quitButton.addActionListener(listener);
	}
	
	public void setConnectListener(ActionListener listener)
	{
		this.connectButton.addActionListener(listener);
	}
	
	public String getUsername()
	{
		return this.usernameText.getText();
	}

	@Override
	public void update() 
	{
		this.frame.update(this);
	}
}
