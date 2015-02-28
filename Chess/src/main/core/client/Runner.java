package core.client;

import gui.ChessFrame;
import gui.GameView;
import gui.LoginView;
import controller.ConnectionController;
import controller.GameWindowController;
import controller.LoginController;

public class Runner {

	public static void main(String[] args) {
		Model model = new Model();
		ChessFrame chessFrame = new ChessFrame();
		
		LoginView loginView = new LoginView(chessFrame);
		//ConnectionView connectView = new ConnectionView(chessFrame);
		//GameView gameView = new GameView(chessFrame);
		
		LoginController loginController = new LoginController(model, loginView);
		//ConnectionController connectionController = new ConnectionController(model, connectView);
		//GameWindowController gameWindowController = new GameWindowController(model, gameView);
	}
}
