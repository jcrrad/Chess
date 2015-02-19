package controller;

import gui.View;
import core.client.Model;

public class ConnectionController 
{
	private View view;
	private Model model;
	
	public void connect() 
	{
		//TODO: This is where we will make a call to the model to connect to the server
		//model.createConnection(); etc.
		System.out.println("ControllerConnect");
		view.goToGame();
	}
	
	public void killWindow()
	{
		view.dispose();
	}
	
	public ConnectionController(Model model)
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
