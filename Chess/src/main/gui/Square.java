package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class Square extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617862454207390583L;
	// Piece piece;
	Color color;
	int row, column;

	// private static Piece movingPiece = null;

	public Square(int x, int y, Color color) {
		this.column = x;
		this.row = y;
		this.color = color;
		this.setBackground(color);
		this.setOpaque(true);
		// this.addActionListener(this);
		this.setMinimumSize(new Dimension(50, 50));
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

	public Color getColor() {
		return this.color;
	}
}
