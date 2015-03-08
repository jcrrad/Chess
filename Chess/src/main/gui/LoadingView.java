package gui;

import javax.swing.JLabel;

public class LoadingView extends View {

	private static final long serialVersionUID = 1L;

	public LoadingView(ChessFrame frame) {
		super(frame);
		this.add(new JLabel("Trying to Connect to Server"));
	}
}
