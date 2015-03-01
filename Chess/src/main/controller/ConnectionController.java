package controller;

import gui.ChessFrame;
import core.client.Model;
import core.client.Model.STATE;

public class ConnectionController extends Controller {

	public ConnectionController(Model model, ConnectionView view) {
		super(model, view);
	}

	@Override
	public void update() 
	{
		if(model.getState() == STATE.CONNECTING)
		{
			System.out.println("Trying to connect.");
			//model.TryConnectToSever();
			model.setState(STATE.INGAME);
		}
	}
}