package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton quit = new JButton("Quit");
	JButton stalemate = new JButton("Offer Stalemate");
	View parentView;

	public ButtonPanel(View parent) {
		parentView = parent;
		this.setLayout(new GridLayout(0, 4, 0, 0));
		this.add(quit);
		this.add(stalemate);
		this.add(new JButton("Place holder for NOW"));
		this.add(new JButton("You Suck"));

		quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				kill();
			}
		});
		stalemate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				offerStalemate();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quitGame();
			}
		});
	}

	public void kill() {
		parentView.kill();
	}

	public void quitGame() {
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			parentView.goToLogin();
	}

	public void offerStalemate() {
		parentView.offerStalemate();
	}
}
