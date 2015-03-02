package controller.piece;

import gui.BoardPanel;
import gui.Square;

import java.awt.Color;

public abstract class Piece {

	String name;
	Color color;
	Square square;

	public Piece(Square square) {
		this.square = square;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Color getColor() {
		return color;
	}

	public String toString() {
		return name + "\t" + color + "\t" + square.getColumn() + "," + square.getRow();
	}

	public boolean moveable(Square square2) {
		if (this.canMove(square2)) {
			BoardPanel board = (BoardPanel) square.getParent();
			return board.walk(this.getSquare(), square2);
		}
		return false;
	}
	
	public abstract boolean canMove(Square square2);

	public boolean canAttack(Square square2) {
		return moveable(square2);
	}
}