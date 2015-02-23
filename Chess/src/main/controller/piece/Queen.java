package controller.piece;

import gui.Square;

import java.awt.Color;

public class Queen extends Piece {

	public Queen(Color color, Square square) {
		super(square);
		this.name = "QUEEN";
		this.color = color;
	}

	@Override
	void move() {
		// TODO Auto-generated method stub

	}

	@Override
	boolean rule(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMove(Square square2) {
		// same row (rook movement)
		if (this.getSquare().getX() == square2.getX())
			return true;
		// same column (rook movement)
		if (this.getSquare().getY() == square2.getY())
			return true;
		// diagonal (bishop) movement
		int xDiff = Math.abs(square.getColumn() - square2.getColumn());
		int yDiff = Math.abs(square.getRow() - square2.getRow());
		if (xDiff == yDiff)
			return true;

		return false;
	}
}
