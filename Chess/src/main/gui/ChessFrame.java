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

	public ChessFrame() {
		super("Chess");

		macSetup("swing-mac");

		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		cardLayout = new CardLayout();

		//setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() > 900)
			setSize(900, 900);
		else
			setSize(700, 700);
		
		this.add(cards);
	}

	public void register(JPanel panel)
	{
		cards.add(panel, panel.getName());
	}
	
	public void update(JPanel panel)
	{
		if(current != null)
		{
			this.remove(current);
		}
		
		this.current = panel;
		this.add(current);
		this.setVisible(true);
	
		//cardLayout.show(cards, panel.getName());
	}
	
	public void kill() {
		// TODO KILL EVERYTHING
		this.dispose();

	}
}
