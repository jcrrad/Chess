package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	ChessFrame parentView;
	JButton goBack = new JButton("Go Back");

	public AboutScreen(ChessFrame parent) {
		parentView = parent;
		this.add(new JLabel("About Screen"));
		this.add(goBack);
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				goBack();
			}
		});
	}

	private void goBack() {
		parentView.goToLogin();
	}
}
