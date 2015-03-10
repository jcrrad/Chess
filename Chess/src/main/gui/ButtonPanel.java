package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton quit = new JButton("Quit");
	JButton stalemate = new JButton("Offer Stalemate");

	public ButtonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(quit);
		this.add(stalemate);
	}
}
