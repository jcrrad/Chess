package controller.piece;

import gui.Square;

import java.awt.Color;

public class Knight extends Piece {

	public Knight(Color color, Square square) {
		super(square);
		this.name = "KNIGHT";
		this.color = color;
	}

	@Override
	public boolean canMove(Square square2) {
		int xDiff = Math.abs(square.getColumn() - square2.getColumn());
		int yDiff = Math.abs(square.getRow() - square2.getRow());
		if (xDiff == 2 && yDiff == 1)
			return true;
		if (xDiff == 1 && yDiff == 2)
			return true;
		return false;
	}

	public boolean moveable(Square square2) {
		return canMove(square2);
	}
}
