package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class RecordPanel extends JPanel {
	public RecordPanel() {

		setLayout(new GridLayout(1, 1, 0, 0));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		JTextField textField = new JTextField("Game moves go HERE");
		this.add(textField, gbc);
	}
}
