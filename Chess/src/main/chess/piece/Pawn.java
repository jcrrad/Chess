package chess.piece;
import java.awt.Color;

import chess.userinterface.Square;

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
}
