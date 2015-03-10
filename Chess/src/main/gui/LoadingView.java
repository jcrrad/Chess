package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoadingView extends View {

	private static final long serialVersionUID = 1L;
	private JLabel status = new JLabel("Trying to connect to server");

	public LoadingView(ChessFrame frame) {
		super(frame);
		GridLayout layout = new GridLayout();
		setLayout(layout);
		status.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(status);
	}

	public void setStatus(String string) {
		this.status.setText(string);
	}
}
