package core.client;

import gui.View;
import controller.ConnectionController;
import controller.GameWindowController;

public class Runner {

	public static void main(String[] args) 
	{
		Model model = new Model();
		
		GameWindowController gameWindowController = new GameWindowController(model);
		ConnectionController connectionController = new ConnectionController(model);
		
		View view = new View(gameWindowController, connectionController);
		
		gameWindowController.setView(view);
		connectionController.setView(view);
	}

}
