package controller;

import gui.BoardPanel;
import gui.View;

import java.io.IOException;
import java.net.UnknownHostException;

import core.client.Client;
import core.client.Connection;
import core.client.InputHandler;
import core.client.Message;
import core.client.Model;

public class GameWindowController 
{
	private View view;
	private Model model;
	private Client client;
	private Connection connection;
	Message message;

	public GameWindowController(Model model) throws UnknownHostException, IOException
	{
		this.model = model;
	}
	
	public void sendChat(String text) 
	{
		updateChat(text);
		message = new Message();
		message.setChatMessage(true);
		message.setText(text);
		connection.send(message);
	}
	
	public void sendMove()
	{
		message = new Message();
		message.setBoardMessage(true);
		message.setBoard(view.getGameScreen().getBoard());
		connection.send(message);
	}
	
	public void processInput(Message message)
	{
		if(message.chatMessage)
			this.updateChat(message.getText());
		else
			view.getGameScreen().setBoard(
					message.getBoard());
	}
	
	public void connect()
	{
		try {
			this.connection = new Connection("172.17.121.82", 8000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new InputHandler(connection, this)).start();
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
