import java.awt.Color;

import javax.swing.JPanel;

public class Square extends JPanel {
	Piece piece;
	Color color;
	int row, column;

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Square(int x, int y, Color color) {
		this.row = x;
		this.column = y;
		this.color = color;
		this.setBackground(color);
	}
}
