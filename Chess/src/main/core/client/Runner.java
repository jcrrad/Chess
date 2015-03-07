package core.client;

import javax.swing.UnsupportedLookAndFeelException;

import gui.AboutView;
import gui.ChessFrame;
import gui.GameView;
import gui.LoadingView;
import gui.LoginView;
import controller.AboutController;
import controller.ConnectionController;
import controller.GameWindowController;
import controller.LoginController;

public class Runner {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Model model = new Model();
		ChessFrame chessFrame = new ChessFrame();
		
		LoginView loginView = new LoginView(chessFrame);
		LoadingView connectView = new LoadingView(chessFrame);
		GameView gameView = new GameView(chessFrame);
		AboutView aboutView = new AboutView(chessFrame);
		
		new LoginController(model, loginView);
		new AboutController(model, aboutView);
		new GameWindowController(model, gameView);
		new ConnectionController(model, connectView);
		
	}
}
