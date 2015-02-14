package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonPanel() {
		this.setLayout(new GridLayout(0, 4, 0, 0));
		this.add(new JButton("Quit"));
		this.add(new JButton("StaleMate"));
		this.add(new JButton("Place holder for NOW"));
		this.add(new JButton("You Suck"));
	}
}
