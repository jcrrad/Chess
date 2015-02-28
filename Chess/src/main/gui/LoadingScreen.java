package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	ChessFrame parentView;

	public LoadingScreen(ChessFrame parent) {
		parentView = parent;
		this.add(new JLabel("Trying to Connect to Server"));
	}
}
