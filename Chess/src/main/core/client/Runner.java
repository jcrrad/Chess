package core.client;

import gui.View;
import controller.GameWindowController;

public class Runner {

	public static void main(String[] args) 
	{
		Model model = new Model();
		
		GameWindowController gameWindowController = new GameWindowController(model);
		
		View view = new View(gameWindowController);
		
		gameWindowController.setView(view);
	}

}
