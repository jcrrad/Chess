package gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton quit = new JButton("Quit");

	public ButtonPanel() {
		this.setLayout(new GridLayout(0, 4, 0, 0));
		this.add(quit);
	}
}
