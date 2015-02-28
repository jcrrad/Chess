package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.ConnectionController;
import controller.GameWindowController;

public class ChessFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel cards = new JPanel();
	GameView gameScreen;
	LoginScreen login;
	LoadingScreen loading;
	AboutScreen about;
	CardLayout cardLayout;
	GameWindowController gameWindowController;
	ConnectionController connectionController;

	private static void macSetup(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		boolean isMac = os.startsWith("mac os x");

		if (!isMac)
			return;

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				appName);
	}

	public ChessFrame(GameWindowController gameWindowController,
			ConnectionController connectionController) {
		super("Chess");
		this.gameWindowController = gameWindowController;
		this.connectionController = connectionController;

		gameScreen = new GameView(this);
		login = new LoginScreen(this, connectionController);
		loading = new LoadingScreen(this);
		about = new AboutScreen(this);

		macSetup("swing-mac");

		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		cardLayout = new CardLayout();

		setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() > 900)
			setSize(900, 900);
		else
			setSize(700, 700);
		this.add(cards);
		cards.setLayout(cardLayout);
		cards.add(about, "about");
		cards.add(loading, "loading");
		cards.add(login, "login");
		cards.add(gameScreen, "game");
		goToLogin();
		// goToGame();
	}

	public void goToGame() {
		System.out.println("Game");
		cardLayout.show(cards, "game");
	}

	public void goToLogin() {
		System.out.println("Login");
		cardLayout.show(cards, "login");
	}

	public void goToLoading() {
		System.out.println("Loading");
		cardLayout.show(cards, "loading");
		goToGame();
	}

	public void goToAbout() {
		System.out.println("About");
		cardLayout.show(cards, "about");
	}

	public void kill() {
		// TODO KILL EVERYTHING
		this.dispose();

	}

	public void register() {
		// TODO Auto-generated method stub

	}
}
