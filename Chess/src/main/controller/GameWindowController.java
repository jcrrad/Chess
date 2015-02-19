package controller;

import gui.View;
import core.client.Model;

public class GameWindowController 
{
	private View view;
	private Model model;

	public void sendMessage(String text) 
	{
		/*TODO: This will be the method to send messages out to the server, this controller will
		also receive messages, we should keep the null check as this will keep us from sending blank
		messages back and forth and wasting resources*/
		if(text.length() != 0)
			System.out.println(text);
			//model.sendMessage();
	}
	
	public void handleQuit() 
	{
		/*TODO: This will be the method called when a player quits, the player who does will
		be returned to the login screen, but the opposing player needs to be alerted.*/	
		System.out.println("Quit Requested");
	}
	
	public void offerStalemate() 
	{
		//TODO: This will be called when one person offers a stalemate	
		System.out.println("Stalemate Offered");
	}
	
	public void killWindow()
	{
		view.dispose();
	}
	
	public GameWindowController(Model model)
	{
		this.model = model;
	}
	
	public View getView() 
	{
		return view;
	}
	
	public void setView(View view) 
	{
		this.view = view;
	}

}
