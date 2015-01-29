package gui.piece;
import gui.Square;

import java.awt.Color;

public class Bishop extends Piece {

	public Bishop(Color color, Square square) {
		super(square);
		this.name = "BISHOP";
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
}
