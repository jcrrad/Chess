package gui;

import gui.piece.Piece;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Square extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617862454207390583L;
	Piece piece;
	Color color;
	int row, column;
	private static Piece movingPiece = null;

	public Square(int x, int y, Color color) {
		this.row = x;
		this.column = y;
		this.color = color;
		this.setBackground(color);
		this.addActionListener(this);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		this.setForeground(piece.getColor());
		this.setText(piece.getName());
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

	public Piece removePiece() {
		Piece temp = piece;
		piece = null;
		System.out.println(temp);
		return temp;
	}

	public void placePiece(Piece newPiece) {
		piece = newPiece;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// clicked on, player is trying to move a piece.

		if (movingPiece != null) {
			movingPiece = removePiece();
			System.out.println(movingPiece.toString());
		} else {
			if (piece == null)
				placePiece(movingPiece);
		}
		if (piece == null)
			this.setText("");
		else
			this.setText(piece.getName());
		invalidate();
		System.out.println(piece.toString());
	}
}
