package chess.piece;
import java.awt.Color;

import chess.userinterface.Square;

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
}
