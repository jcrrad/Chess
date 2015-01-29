package chess.piece;
import java.awt.Color;

import chess.userinterface.Square;

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
}
