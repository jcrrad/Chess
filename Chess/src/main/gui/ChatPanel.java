package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GameWindowController;

public class ChatPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField inputField;

	// TODO: JEFF - make scrollable
	public ChatPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 450, 0 };
		gridBagLayout.rowHeights = new int[] { 150, 150, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JTextField conversationField = new JTextField();
		GridBagConstraints conv_gbc = new GridBagConstraints();
		conv_gbc.fill = GridBagConstraints.BOTH;
		conv_gbc.gridwidth = GridBagConstraints.REMAINDER;
		conv_gbc.weighty = 25.0;
		this.add(conversationField, conv_gbc);
		conversationField.setEnabled(false);
		conversationField.setScrollOffset(HEIGHT);

		inputField = new JTextField();
		GridBagConstraints input_gbc = new GridBagConstraints();
		input_gbc.insets = new Insets(10, 0, 0, 5);
		input_gbc.gridy = 1;
		input_gbc.weighty = 0.1;
		input_gbc.weightx = 5;
		input_gbc.fill = GridBagConstraints.BOTH;
		this.add(inputField, input_gbc);

		JButton button = new JButton();
		button.setText("Submit");
		GridBagConstraints button_gbc = new GridBagConstraints();
		button_gbc.insets = new Insets(10, 5, 0, 0);
		button_gbc.gridy = 1;
		button_gbc.fill = GridBagConstraints.BOTH;
		button_gbc.weighty = 0.1;
		button_gbc.weighty = 1;
		button_gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(button, button_gbc);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// gameWindowController.sendMessage(inputField.getText());
				// inputField.setText("");
			}
		});

	}

}
