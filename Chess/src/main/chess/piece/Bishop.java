package chess.piece;
import java.awt.Color;

import chess.userinterface.Square;

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
}
