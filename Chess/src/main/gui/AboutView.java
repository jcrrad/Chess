package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutView extends JPanel implements View{
	private static final long serialVersionUID = 1L;
	
	JButton goBack = new JButton("Go Back");

	public AboutView(ChessFrame frame) {
		frame.register(this);
		this.add(new JLabel("About Screen"));
		this.add(goBack);
	}
	
	public void setGoBackListener(ActionListener listener)
	{
		goBack.addActionListener(listener);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
