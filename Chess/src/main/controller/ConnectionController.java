package controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

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
			view.setStatus("Trying to connect to server.");
			view.update();
			try {
				createConnection();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view, "Server connection cannot be established. Check to make sure that the server is running and try again.",
						"ServerUnavailable", JOptionPane.INFORMATION_MESSAGE);
				model.setState(STATE.LOGIN);
			}
			
			model.setConnection(serverConnection);
			handler = new InputHandler(serverConnection, this);
			new Thread(handler).start();
		}
		else if(model.getState() == STATE.QUIT)
		{
			Message m = new Message();
			m.setDisconnected(true);
			if(this.serverConnection != null)
			{
				this.serverConnection.send(m);
				this.serverConnection.disconnect();
			}
		}
	}
	
	private void createConnection() throws UnknownHostException, IOException
	{
		this.serverConnection = new Connection(model.getServerHostname(), model.getServerPort());
	}
	
	public void processInput(Message message)
	{
		if(message.isDisconnected())
		{
			view.setStatus("The Opponent disconnected");
			view.update();
			//Post a button
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			model.setState(STATE.LOGIN);
		}
		else if(model.getState() == STATE.CONNECTING && message.isClientsTurn())
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
