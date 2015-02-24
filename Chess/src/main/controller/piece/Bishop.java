package controller.piece;

import gui.Square;

import java.awt.Color;

public class Bishop extends Piece {

	public Bishop(Color color, Square square) {
		super(square);
		this.name = "BISHOP";
		this.color = color;
	}

	@Override
	public boolean canMove(Square square2) {
		int xDiff = Math.abs(square.getColumn() - square2.getColumn());
		int yDiff = Math.abs(square.getRow() - square2.getRow());
		if (xDiff == yDiff)
			return true;
		return false;
	}
}
