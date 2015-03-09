package gui;

import static org.junit.Assert.*;

import javax.swing.UnsupportedLookAndFeelException;

import org.junit.Test;

import controller.AboutController;
import controller.ConnectionController;
import controller.GameWindowController;
import controller.LoginController;
import core.client.Model;

public class GuiTest {
	@Test
	public void TestFrame() {
		try {
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

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
