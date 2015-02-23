package controller.piece;

import gui.Square;

import java.awt.Color;

public class King extends Piece {

	public King(Color color, Square square) {
		super(square);
		this.name = "KING";
		this.color = color;
	}

	@Override
	public boolean canMove(Square square2) {
		// TODO Auto-generated method stub
		return false;
	}
}
