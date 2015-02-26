package controller.piece;

import gui.Square;

import java.awt.Color;
import java.io.Serializable;

public abstract class Piece implements Serializable{

	private static final long serialVersionUID = 1L;
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

	public abstract boolean canMove(Square square2);
}