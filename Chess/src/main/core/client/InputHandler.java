package core.client;

import controller.GameWindowController;

public class InputHandler implements Runnable{
	
	private Connection connection;
	private GameWindowController controller;

	public InputHandler(Connection con, GameWindowController controller)
	{
		this.connection = con;
		this.controller = controller;
	}

	@Override
	public void run() 
	{
		String inputLine;
		while ((inputLine = connection.receive()) != null) {
			this.controller.processInput(inputLine);
	    }
	}

}
