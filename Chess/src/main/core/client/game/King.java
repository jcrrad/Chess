package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class King extends Piece {

	public King(Board board, Color color, Coordinate location) {
		super(board, location);
		this.name = "KING";
		this.color = color;
	}

	@Override
	public boolean canMove(Coordinate location) {
		int xDiff = Math.abs(this.location.getX() - location.getX());
		int yDiff = Math.abs(this.location.getY() - location.getY());
		if (xDiff <= 1 && yDiff <= 1)
			return true;
		return false;
	}
}
