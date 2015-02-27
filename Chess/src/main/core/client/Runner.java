package core.client;

import java.io.IOException;
import java.net.UnknownHostException;

import gui.View;
import controller.GameWindowController;

public class Runner {

	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Model model = new Model();
		
		GameWindowController gameWindowController = new GameWindowController(model);
		
		View view = new View(gameWindowController);
		
		gameWindowController.setView(view);

	}

}
