package gui;

import javax.swing.JPanel;

public abstract class View extends JPanel{

	private static final long serialVersionUID = -254429680469964291L;
	private ChessFrame frame;
	
	public View(ChessFrame frame)
	{
		this.frame = frame;
	}
	public void update()
	{
		this.frame.update(this);
	}

}
