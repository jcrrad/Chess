package core.client;

import controller.ConnectionController;
import controller.GameWindowController;

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
