package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.ConnectionController;
import controller.GameWindowController;

public class ChessFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel cards = new JPanel();
	JPanel current;
	CardLayout cardLayout;

	private static void macSetup(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		boolean isMac = os.startsWith("mac os x");

		if (!isMac)
			return;

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				appName);
	}

	public ChessFrame() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		super("Chess");

		macSetup("swing-mac");
		// this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		setScreenSize();
	}

	private void setScreenSize() {
		setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());

	}

	public void register(JPanel panel) {
		cards.add(panel, panel.getName());
	}

	public void update(JPanel panel) {
		if (current != null) {
			this.current.setVisible(false);
			this.remove(current);
		}
		this.current = panel;
		this.add(current);
		panel.setVisible(true);
		this.setVisible(true);
	}

	public void kill() {
		// TODO KILL EVERYTHING
		this.dispose();

	}
}
