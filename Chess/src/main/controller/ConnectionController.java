package controller;

import gui.ChessFrame;
import core.client.Model;

public class ConnectionController extends Controller {

	public ConnectionController(Model model, ConnectionView view) {
		super(model, view);
	}

	@Override
	public void update() 
	{
		if(model.getState() == "connecting")
		{
			System.out.println("Trying to connect.");
			model.setState("ingame");
		}
	}
}
