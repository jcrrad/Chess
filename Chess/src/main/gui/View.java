package gui;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel cards = new JPanel();
	GameScreen gameScreen;
	LoginScreen login;
	LoadingScreen loading;
	AboutScreen about;
	CardLayout cardLayout;

	public View() {
		super("Chess");
		gameScreen = new GameScreen(this);
		login = new LoginScreen(this);
		loading = new LoadingScreen(this);
		about = new AboutScreen(this);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		// goToLogin();
		goToGame();
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

	public void offerStalemate() {
		// TODO Offer opponent stalemate

	}

	public void sendMessage() {
		// TODO send Message

	}
}
