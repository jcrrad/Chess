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
		int x1 = this.location.getX(), y1 = this.location.getY();
		int x2 = location.getX(), y2 = location.getY();
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);

		// Diagonal Attack
		if (xdiff == 1 && ydiff == 1
				&& !this.board.getPiece(location).getName().equals("")
				&& !this.board.getPiece(location).getColor().equals(this.color)) {
			if ((this.color.equals(Color.BLACK) && y1 > y2)
					|| (this.color.equals(Color.WHITE) && y1 < y2)) {
				return true;
			}
		}
		return false;
	}

	public boolean canMove(Coordinate location) {
		int x1 = this.location.getX(), y1 = this.location.getY();
		int x2 = location.getX(), y2 = location.getY();
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);

		if (xdiff == 0 && this.board.getPiece(location).getName().equals("")) {
			// Double Move
			if (ydiff == 2
					&& ((this.color.equals(Color.BLACK) && y1 == 6) || (this.color
							.equals(Color.WHITE) && y1 == 1))) {
				return true;
			}
			// Single Move
			if (ydiff == 1
					&& ((this.color.equals(Color.BLACK) && y1 > y2) || (this.color
							.equals(Color.WHITE) && y1 < y2))) {
				return true;
			}

		}
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