package gui.piece;
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
}
