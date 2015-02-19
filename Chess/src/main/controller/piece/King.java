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
	void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean rule(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
