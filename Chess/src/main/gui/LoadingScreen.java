package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	View parentView;

	public LoadingScreen(View parent) {
		parentView = parent;
		this.add(new JLabel("Trying to Connect to Server"));
	}
}
