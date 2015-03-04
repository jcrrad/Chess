package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class Knight extends Piece {

	public Knight(Board board, Color color, Coordinate location) {
		super(board, location);
		this.name = "KNIGHT";
		this.color = color;
	}

	@Override
	public boolean canMove(Coordinate location) {
		int xDiff = Math.abs(this.location.getX() - location.getX());
		int yDiff = Math.abs(this.location.getY() - location.getY());
		if (xDiff == 2 && yDiff == 1)
			return true;
		if (xDiff == 1 && yDiff == 2)
			return true;
		return false;
	}

	public boolean moveable(Coordinate location) {
		return canMove(location);
	}

	@Override
	public String getSymbol() {
		if (color.equals(Color.BLACK))
			return "N";
		else
			return "n";
	}
}
