package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.ChessFrame;
import core.client.Connection;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;

public class ConnectionController extends Controller {

	Connection serverConnection;
	InputHandler handler;
	
	public ConnectionController(Model model, ConnectionView view) {
		super(model, view);
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
			handler = new InputHandler(serverConnection, this);
			new Thread(handler).start();
		}
		else if(model.getState() == STATE.CHATTING)
		{
			
		}
	}
	
	private void createConnection() throws UnknownHostException, IOException
	{
		this.serverConnection = new Connection(model.getServerHostname(), model.getServerPort());
	}
	
	//Debating on whether this should be in the model or not
	// It conflicts because although data is being handled
	// it is coming from a socket which model shouldnt touch.
	// a layer of indirection would need to go here but this might not be best.
	// however this could be advantagous because the controller doesnt know what methods should be called when updating.
	public void processInput(Message message)
	{
		System.out.println("processing");
		if(message.hasChat())
		{
			model.setCurrentChat(message.getChatText());
		}
		if(message.hasBoardUpdate())
		{
			model.updateBoard(message.getBoard());
			model.setBoardOwner(true);
		}
		// this is too odd.
		if(model.getState() == STATE.CONNECTING && message.isClientsTurn())
		{
			model.setBoardOwner(true);
			model.setState(STATE.INGAME);
		}
		model.setState(STATE.INGAME);
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