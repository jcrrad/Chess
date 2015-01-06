import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Board extends JPanel {
	Square[][] squares = new Square[8][8];

	public Board() {
		this.setLayout(new GridLayout(8, 8));
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++) {
				Color color;
				if ((x + y) % 2 == 1)
					color = Color.BLACK;
				else
					color = Color.WHITE;
				squares[x][y] = new Square(x, y, color);
				this.add(squares[x][y]);
			}
		for (int x = 0; x < 8; x++) {
			squares[x][0].setPiece(new Pawn(Color.BLACK, squares[x][0]));
			squares[x][7].setPiece(new Pawn(Color.WHITE, squares[x][7]));
		}
	}
}
