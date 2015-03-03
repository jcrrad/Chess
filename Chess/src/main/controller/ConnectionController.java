package controller;

import gui.ConnectionView;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;

public class ConnectionController implements Observer{
	
	private Model model;
	private ConnectionView view;
	
	public ConnectionController(Model model, ConnectionView view) {
		this.model = model;
		this.view = view;
		model.registerObserver(this);
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
	
	@Override
	public void update(Object message) {
		// TODO no default
		
	}
}