package controller;

import javax.swing.JPanel;

import gui.ChessFrame;
import gui.View;

public class ConnectionView extends JPanel implements View {
	
	private static final long serialVersionUID = -3138251686484895595L;
	private ChessFrame frame;
	
	public ConnectionView(ChessFrame frame)
	{
		this.frame = frame;
		this.frame.register(this);
	}

	@Override
	public void update() 
	{
		this.frame.update(this);
	}

}
