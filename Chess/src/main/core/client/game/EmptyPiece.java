package core.client.game;

import java.awt.Color;

import core.client.Coordinate;

public class EmptyPiece extends Piece{
	
	public EmptyPiece(Board board, Color color, Coordinate location){
		super(board, location);
		this.name = "";
		this.color = Color.BLUE;
	}
	
	public boolean canMove(Coordinate location){
		return false;
	}

}
