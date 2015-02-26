package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.piece.Piece;

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
		this.column = x;
		this.row = y;
		this.color = color;
		this.setBackground(color);
		this.addActionListener(this);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece newPiece) {
		piece = newPiece;
		piece.setSquare(this);
		newPiece = null;
		this.updateSquare();
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
		return temp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// clicked on, player is trying to move a piece.
		if (movingPiece == null && piece != null) {// player is selecting which
													// piece to move
			movingPiece = removePiece();
			System.out.println("Piece picked up" + movingPiece.toString());
		} else {// player has a piece in their hand
			if ((piece == null || !(piece.getColor().equals(movingPiece.getColor()))))
				if (movingPiece.moveable(this)) {
					// if moving piece can move to new square
					setPiece(movingPiece);
					movingPiece = null;
				} else {// move failed put it back
					movingPiece.getSquare().setPiece(movingPiece);
					movingPiece = null;
				}
		}
		this.updateSquare();
	}

	public void updateSquare() {
		if (piece == null) {
			this.setText("");
		} else {
			this.setText(piece.getName());
			this.setForeground(piece.getColor());
			invalidate();
		}

	}

	static String pawnPromotion() {
		String[] choices = { "Knight", "Bishop", "Rook", "Queen" };
		String s = (String) JOptionPane.showInputDialog(null, "make your choice", "Try GUI", JOptionPane.PLAIN_MESSAGE,
				null, choices, choices[0]);
		return s;
	}
}
