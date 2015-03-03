package controller;

import gui.ConnectionView;
import core.client.Model;
import core.client.Model.STATE;

public class ConnectionController extends Controller {
	
	private Model model;
	private ConnectionView view;
	
	public ConnectionController(Model model, ConnectionView view) {
		super(model);
		this.model = model;
		this.view = view;
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