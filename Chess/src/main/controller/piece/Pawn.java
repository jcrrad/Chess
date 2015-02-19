package controller.piece;
import gui.Square;

import java.awt.Color;

public class Pawn extends Piece {
	public Pawn(Color color, Square square) {
		super(square);
		this.name = "PAWN";
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
