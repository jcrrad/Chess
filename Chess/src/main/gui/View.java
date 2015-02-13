package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class View extends JFrame {
	// Board board;
	GameScreen gameScreen = new GameScreen();

	public View() {
		super("Chess");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(1, 1));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		this.add(gameScreen);
	}
}
