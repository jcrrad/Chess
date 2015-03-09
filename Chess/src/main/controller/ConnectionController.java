package controller;

import java.io.IOException;
import java.net.UnknownHostException;

import gui.LoadingView;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;
import core.client.Connection;

public class ConnectionController implements Observer{
	
	private Model model;
	private LoadingView view;
	private Connection serverConnection;
	private InputHandler handler;
	
	public ConnectionController(Model model, LoadingView view) {
		this.model = model;
		this.view = view;
		model.registerObserver(this);
	}

	@Override
	public void update(Object message) 
	{
		// TODO no default
		
	}
	
	@Override
	public void update() 
	{
		if(model.getState() == STATE.CONNECTING)
		{
			view.update();
			try {
				createConnection();
			} catch (UnknownHostException e) {
				//Print that host is bad
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			model.setConnection(serverConnection);
			handler = new InputHandler(serverConnection, this);
			new Thread(handler).start();
		}
	}
	
	private void createConnection() throws UnknownHostException, IOException
	{
		this.serverConnection = new Connection(model.getServerHostname(), model.getServerPort());
	}
	
	public void processInput(Message message)
	{
		if(model.getState() == STATE.CONNECTING && message.isClientsTurn())
		{
			model.setStartingPlayer(true);
			model.setState(STATE.INGAME);
		}
		else
		{
			model.setState(STATE.INGAME);
		}
		
		model.setMessage(message);
	}
	
	public class InputHandler implements Runnable{
		
		private Connection connection;
		private ConnectionController controller;

		public InputHandler(Connection con, ConnectionController controller)
		{
			this.connection = con;
			this.controller = controller;
		}

		@Override
		public void run() 
		{
			Message message;
			while ((message = connection.receive()) != null) {
				this.controller.processInput(message);
		    }
		}

	}
}
