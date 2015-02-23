package controller.piece;

import gui.Square;

import java.awt.Color;

public class Rook extends Piece {

	public Rook(Color color, Square square) {
		super(square);
		this.name = "ROOK";
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
		if (square.getRow() == square2.getRow())
			return true;
		if (square.getColumn() == square2.getColumn())
			return true;
		return false;
	}
}
