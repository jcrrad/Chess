package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutView extends JPanel implements View{
	private static final long serialVersionUID = 1L;
	
	ChessFrame frame;
	JButton goBack = new JButton("Go Back");

	public AboutView(ChessFrame frame) {
		this.frame = frame;
		frame.register(this);
		this.add(new JLabel("About Screen"));
		this.add(goBack);
	}
	
	public void setGoBackListener(ActionListener listener)
	{
		goBack.addActionListener(listener);
	}

	@Override
	public void update() 
	{	
		frame.update(this);
	}
}
