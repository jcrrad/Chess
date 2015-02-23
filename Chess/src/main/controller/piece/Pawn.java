package controller.piece;
import gui.Square;

import java.awt.Color;

public class Pawn extends Piece {
	public Pawn(Color color, Square square) {
		super(square);
		this.name = "PAWN";
		this.color = color;
	}

	public boolean canMove(Square square2){
		// Diagonal Attack
		if ((Math.abs(square.getRow()-square2.getRow()) == 1) && (Math.abs(square.getColumn()-square2.getColumn()) == 1) && (square2.getPiece() != null)){
			if (((this.color.equals(Color.BLACK)) && (square.getRow() > square2.getRow()) ) || ((this.color.equals(Color.WHITE)) && (square.getRow() < square2.getRow())))
				return true;
		}
		if (Math.abs(square.getColumn()-square2.getColumn()) > 0)
			return false;
		
		// Double Move
		if ((Math.abs(square.getRow()-square2.getRow()) == 2) && (square2.getPiece() == null)){
			if (((this.color.equals(Color.BLACK)) && (square.getRow() == 6)) || ((this.color.equals(Color.WHITE)) && (square.getRow() == 1)))
				return true;
		}
		if (Math.abs(square.getRow()-square2.getRow()) > 1)
			return false;
		
		// Single Move
		if ((Math.abs(square.getRow()-square2.getRow()) == 1) && (square2.getPiece() == null)){
			if (((this.color.equals(Color.BLACK)) && (square.getRow() > square2.getRow()) ) || ((this.color.equals(Color.WHITE)) && (square.getRow() < square2.getRow())))
				return true;
		}
		
		// otherwise
		return false;
	}
	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean rule(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
