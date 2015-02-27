package controller.piece;

import gui.Square;

import java.awt.Color;

public class Rook extends Piece {

	public Rook(Color color, Square square) {
		super(square);
		this.name = "ROOK";
		this.color = color;
	}

	public boolean canMove(Square square2) { // same row (rook movement)
		if (this.getSquare().getX() == square2.getX())
			return true;
		// same column (rook movement)
		if (this.getSquare().getY() == square2.getY())
			return true;
		return false;
	}
}
