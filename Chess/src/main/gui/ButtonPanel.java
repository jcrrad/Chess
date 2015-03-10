package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton quit = new JButton("Quit");
	
	public ButtonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(quit);
	}
}
