package chess.piece;
import java.awt.Color;

import chess.userinterface.Square;

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
}
