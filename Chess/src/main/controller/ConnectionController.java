package controller;

import java.io.IOException;
import java.net.UnknownHostException;

import gui.ConnectionView;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;
import core.client.Connection;

public class ConnectionController implements Observer{
	
	private Model model;
	private ConnectionView view;
	private Connection serverConnection;
	private InputHandler handler;
	
	public ConnectionController(Model model, ConnectionView view) {
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
			System.out.println("Connecting");
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
			model.setPlayerTurn(true);
			model.setState(STATE.INGAME);
		}
		else
		{
			model.setState(STATE.INGAME);
		}
		
		model.setMessage(message);
		//System.out.println("received a message");
		//if(model.getState() == STATE.WAITING && message.hasReconnected() == true)
		//{
		//	System.out.println("Received reconnection notice");
		//	Message response = new Message();
		//	response.setClientsTurn(!model.getBoardOwner());
		//	serverConnection.send(response);
		//	model.setState(STATE.INGAME);
		//	//send the board state over
		//}else if(model.getState() == STATE.INGAME && message.isDisconnected() == true)
		//{
		//	System.out.println("Received disconnection notice");
		//	model.setState(STATE.WAITING);
		//}
		//else
		//{
		//	System.out.println("processing");
		//	if(message.hasChat())
		//	{
		//		System.out.println("Has chat stuff");
		//		model.setCurrentChat(message.getChatText());
		//	}
		//	if(message.hasBoardUpdate())
		//	{
		//		model.updateBoard(message.getBoard());
		//		model.setBoardOwner(true);
		//	}
		//	// this is too odd.
		//	if(model.getState() == STATE.CONNECTING && message.isClientsTurn())
		//	{
		//		model.setBoardOwner(true);
		//		model.setState(STATE.INGAME);
		//	}
		//	model.setState(STATE.INGAME);
		//}
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
				System.out.println("sending to process");
				this.controller.processInput(message);
		    }
		}

	}
}
