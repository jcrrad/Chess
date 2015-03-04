package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AboutView extends View{
	private static final long serialVersionUID = 1L;
	
	//ChessFrame frame;
	JButton goBack = new JButton("Go Back");
	JLabel title = new JLabel();
	JLabel description = new JLabel();
	JLabel version = new JLabel();
	JLabel copywrite = new JLabel();
	JLabel authors = new JLabel();
	JLabel rdate = new JLabel();

	public AboutView(ChessFrame frame) {
		super(frame);
		//this.frame = frame;
		//frame.register(this);
		
		this.add(title);
		this.add(description);
		this.add(version);
		this.add(copywrite);
		this.add(authors);
		this.add(rdate);
		this.add(new JLabel("About Screen"));
		this.add(goBack);
	}
	
	public void setGoBackListener(ActionListener listener)
	{
		goBack.addActionListener(listener);
	}
	
	public void setTitle(String title)
	{
		this.title.setText(title);
	}
	
	public void setDescription(String description)
	{
		this.description.setText(description);
	}
	
	public void setVersion(String version)
	{
		this.version.setText(version);
	}
	
	public void setCopyWrite(String cp)
	{
		this.copywrite.setText(cp);
	}
	
	public void setAuthors(String authors)
	{
		this.authors.setText(authors);
	}
	
	public void setReleaseDate(String date)
	{
		this.rdate.setText(date);
	}
}
