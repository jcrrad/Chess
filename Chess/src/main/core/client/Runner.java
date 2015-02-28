package core.client;

import gui.ChessFrame;
import controller.ConnectionController;
import controller.GameWindowController;

public class Runner {

	public static void main(String[] args) {
		Model model = new Model();

		GameWindowController gameWindowController = new GameWindowController(
				model);
		ConnectionController connectionController = new ConnectionController(
				model);

		ChessFrame view = new ChessFrame(gameWindowController, connectionController);

		gameWindowController.setView(view);
		connectionController.setView(view);
	}

}
