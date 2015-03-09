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
import core.client.Model.STATE;

public class Runner {

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		String hostname = "localhost";
		int port = 8000;
		
		if(args.length > 1)
		{
			args = args[0].split(":");
			if(args.length != 2)
			{
				System.out.println("Please enter the location of server. ex: localhost:8000");
				return;
			}
				hostname = args[0];
				port = Integer.parseInt(args[1]);
		}
		
		Model model = new Model(hostname,port);
		ChessFrame chessFrame = new ChessFrame();

		LoginView loginView = new LoginView(chessFrame);
		GameView gameView = new GameView(chessFrame);
		AboutView aboutView = new AboutView(chessFrame);
		LoadingView loadingView = new LoadingView(chessFrame);

		new LoginController(model, loginView);
		new AboutController(model, aboutView);
		new GameWindowController(model, gameView);
		new ConnectionController(model, loadingView);
		
		model.setState(STATE.LOGIN);
		
	}
}
