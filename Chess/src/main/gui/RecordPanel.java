package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecordPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8787229184202768459L;

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
