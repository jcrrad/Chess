package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class Rook extends Piece {

	public Rook(Board board, Color color, Coordinate location) {
		super(board, location);
		this.name = "ROOK";
		this.color = color;
	}

	public boolean canMove(Coordinate location) { // same row (rook movement)
		if (this.location.getX() == location.getX())
			return true;
		// same column (rook movement)
		if (this.location.getY() == location.getY())
			return true;
		return false;
	}

	@Override
	public String getSymbol() {
		if (color.equals(Color.BLACK))
			return "R";
		else
			return "r";
	}
}
