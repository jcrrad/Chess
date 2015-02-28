package core.client;

import gui.AboutView;
import gui.ChessFrame;
import gui.GameView;
import gui.LoginView;
import controller.AboutController;
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
		AboutView aboutView = new AboutView(chessFrame);
		
		LoginController loginController = new LoginController(model, loginView);
		AboutController aboutController = new AboutController(model, aboutView);
		
		//ConnectionController connectionController = new ConnectionController(model, connectView);
		//GameWindowController gameWindowController = new GameWindowController(model, gameView);
	}
}
