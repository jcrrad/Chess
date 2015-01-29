package chess.userinterface;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class ChessFrame extends JFrame {
	Board board;

	public ChessFrame() {
		super("Chess");
		this.setLayout(new GridLayout(1, 1));
		board = new Board();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		this.add(board);
	}
}
