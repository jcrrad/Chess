package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class LoginScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginScreen() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 450, 0 };
		gridBagLayout.rowHeights = new int[] { 70, 70, 70, 75, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel welcome = new JLabel("Welcome to Chess");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setLabelFor(this);
		GridBagConstraints gbc_welcome = new GridBagConstraints();
		gbc_welcome.fill = GridBagConstraints.BOTH;
		gbc_welcome.insets = new Insets(0, 0, 5, 0);
		gbc_welcome.gridx = 0;
		gbc_welcome.gridy = 0;
		this.add(welcome, gbc_welcome);

		JLabel username = new JLabel("What is your username?");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.anchor = GridBagConstraints.SOUTH;
		gbc_username.fill = GridBagConstraints.BOTH;
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.gridx = 0;
		gbc_username.gridy = 1;
		this.add(username, gbc_username);

		JTextField input = new JTextField();
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.insets = new Insets(0, 0, 5, 0);
		gbc_input.gridx = 0;
		gbc_input.gridy = 2;
		this.add(input, gbc_input);

		JButton connectButton = new JButton("Connect");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		add(connectButton, gbc_btnNewButton);

		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO Connect
			}
		});
	}
}
