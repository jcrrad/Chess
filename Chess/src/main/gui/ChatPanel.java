package gui;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		JTextField inputField = new JTextField();
		GridBagConstraints input_gbc = new GridBagConstraints();
		input_gbc.gridy = 1;
		input_gbc.weighty = 0.1;
		input_gbc.weightx = 5;
		input_gbc.fill = GridBagConstraints.BOTH;
		this.add(inputField, input_gbc);

		JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO SEND CHAT TEXT TO SERVER
			}
		});
		button.setText("Submit");
		GridBagConstraints button_gbc = new GridBagConstraints();
		button_gbc.gridy = 1;
		button_gbc.fill = GridBagConstraints.BOTH;
		button_gbc.weighty = 0.1;
		button_gbc.weighty = 1;
		button_gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(button, button_gbc);

	}
}
