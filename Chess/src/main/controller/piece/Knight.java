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
		// TODO Auto-generated method stub
		return false;
	}
}
