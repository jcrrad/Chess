package chess.piece;
import java.awt.Color;

import chess.userinterface.Square;

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

	abstract void move();

	public String toString() {
		return name + "\t" + color + "\t" + square.getColumn() + "," + square.getRow();
	}

}
