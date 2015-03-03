package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class Bishop extends Piece {

	public Bishop(Board board, Color color, Coordinate location) {
		super(board, location);
		this.name = "BISHOP";
		this.color = color;
	}

	@Override
	public boolean canMove(Coordinate location) {
		int xDiff = Math.abs(this.location.getX() - location.getX());
		int yDiff = Math.abs(this.location.getY() - location.getY());
		if (xDiff == yDiff)
			return true;
		return false;
	}
}
