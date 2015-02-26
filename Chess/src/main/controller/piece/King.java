package controller.piece;

import gui.Square;

import java.awt.Color;

public class King extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public King(Color color, Square square) {
		super(square);
		this.name = "KING";
		this.color = color;
	}

	@Override
	public boolean canMove(Square square2) {
		int xDiff = Math.abs(square.getColumn() - square2.getColumn());
		int yDiff = Math.abs(square.getRow() - square2.getRow());
		if (xDiff <= 1 && yDiff <= 1)
			return true;
		return false;
	}
}
