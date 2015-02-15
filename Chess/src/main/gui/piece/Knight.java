package gui.piece;
import gui.Square;

import java.awt.Color;

public class Knight extends Piece {

	public Knight(Color color, Square square) {
		super(square);
		this.name = "KNIGHT";
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
