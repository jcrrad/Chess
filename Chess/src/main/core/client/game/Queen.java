package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class Queen extends Piece {

	public Queen(Board board, Color color, Coordinate location) {
		super(board, location);
		this.name = "QUEEN";
		this.color = color;
	}

	@Override
	public boolean canMove(Coordinate location) {
		// diagonal (bishop) movement
		int xDiff = Math.abs(this.location.getX() - location.getX());
		int yDiff = Math.abs(this.location.getY() - location.getY());
		if (xDiff == yDiff)
			return true;

		return false;
	}
}
