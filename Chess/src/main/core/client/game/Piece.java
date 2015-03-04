package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public abstract class Piece {

	Board board;
	String name;
	Color color;
	Coordinate location;
	boolean moved = false;

	public Piece(Board board, Coordinate location) {
		this.board = board;
		this.location = location;
	}

	public boolean hasMoved() {
		return moved;
	}

	public void setMoved() {
		moved = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public Coordinate getLocation() {
		return this.location;
	}

	public Color getColor() {
		return color;
	}

	public String toString() {
		return name + "\t" + color + "\t" + this.location.getX() + ","
				+ this.location.getY();
	}

	public boolean moveable(Coordinate location) {
		if (this.canMove(location)) {
			return this.board.walk(this.location, location);
		}
		return false;
	}

	public abstract boolean canMove(Coordinate location);

	public boolean canAttack(Coordinate location) {
		return moveable(location);
	}

	public abstract String getSymbol();
}