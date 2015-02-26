package controller;

import gui.View;
import core.client.Client;
import core.client.Model;

public class GameWindowController 
{
	private View view;
	private Model model;
	private Client client;

	public GameWindowController(Model model)
	{
		this.model = model;
	}
	
	public void sendMessage(String text) 
	{
		
	}
	
	public void connect() 
	{
		
		view.goToGame();
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
	
	public void updateChat(String text)
	{
		view.updateChat(text);
	}
	
	public void updateBoard(String board)
	{
		//TODO
	}
	
	////////////////////////////////////
	//
	// Getters & Setters
	//
	////////////////////////////////////
	
	public View getView() 
	{
		return view;
	}
	
	public void setView(View view) 
	{
		this.view = view;
	}

}
