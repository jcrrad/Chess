package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class Pawn extends Piece {
	public Pawn(Board board, Color color, Coordinate location) {
		super(board, location);
		this.name = "PAWN";
		this.color = color;
	}

	public boolean canAttack(Coordinate location) {
		// Diagonal Attack
		if ((Math.abs(this.location.getY() - location.getY()) == 1)
				&& (Math.abs(this.location.getX() - location.getX()) == 1)
				&& (this.board.getPiece(location) != null)) {
			if (((this.color.equals(Color.BLACK)) && (this.location.getY() > location
					.getY()))
					|| ((this.color.equals(Color.WHITE)) && (this.location
							.getY() < location.getY())))
				return true;
		}
		return false;
	}

	public boolean canMove(Coordinate location) {
		// Single Move
		if ((Math.abs(this.location.getY() - location.getY()) == 1)
				&& (this.board.getPiece(location) == null)) {
			if (((this.color.equals(Color.BLACK)) && (this.location.getY() > location
					.getY()))
					|| ((this.color.equals(Color.WHITE)) && (this.location
							.getY() < location.getY())))
				return true;
		}

		// Double Move
		if ((Math.abs(this.location.getY() - location.getY()) == 2)
				&& (this.board.getPiece(location) == null)) {
			if (((this.color.equals(Color.BLACK)) && (this.location.getY() == 6))
					|| ((this.color.equals(Color.WHITE)) && (this.location
							.getY() == 1)))
				return true;
		}
		if (Math.abs(this.location.getY() - location.getY()) > 1)
			return false;

		// otherwise
		return canAttack(location);
	}

	@Override
	public String getSymbol() {
		if (color.equals(Color.BLACK))
			return "P";
		else
			return "p";
	}

}
