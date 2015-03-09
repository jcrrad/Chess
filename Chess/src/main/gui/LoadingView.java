package gui;

import javax.swing.JLabel;

public class LoadingView extends View {

	private static final long serialVersionUID = 1L;
	private JLabel status = new JLabel("Trying to connect to server");
	public LoadingView(ChessFrame frame) {
		super(frame);
		this.add(status);
	}

	public void setStatus(String string) 
	{
		this.status.setText(string);
	}
}
