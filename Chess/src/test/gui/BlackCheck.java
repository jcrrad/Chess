package gui;

import static org.junit.Assert.*;
import gui.BoardPanel;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;
import core.client.game.Board;

/**
 * Base test class for {@link BoardPanel}
 * 
 * @author Brisbin
 *
 */
public class BlackCheck {
	@Test
	public void testCheckFalse() {

		Board board = new Board();
		assertFalse(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
	}

	@Test
	public void testCheckBlackIntercept() {
		Board board = new Board();
		Coordinate coord1, coord2;

		// Remove pawns
		coord1 = new Coordinate(3,1);
		board.removePiece(coord1);
		coord1 = new Coordinate(3,6);
		board.removePiece(coord1);
		
		// Move White Queen
		coord1 = new Coordinate(4,0);
		coord2 = new Coordinate(3,1);
		board.movePiece(coord1, coord2);
		
		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckBlackKingMoves() {
		Board board = new Board();
		Coordinate coord1, coord2;
		
		// Remove pawns
		coord1 = new Coordinate(3,1);
		board.removePiece(coord1);
		coord1 = new Coordinate(3,6);
		board.removePiece(coord1);

		// Remove Black Queen
		coord1 = new Coordinate(4,7);
		board.removePiece(coord1);
		
		// Move White Queen
		coord1 = new Coordinate(4,0);
		coord2 = new Coordinate(3,1);
		board.movePiece(coord1, coord2);

		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckBlackKillAssassin() {
		Board board = new Board();
		Coordinate coord1, coord2;

		// Remove pawns
		coord1 = new Coordinate(3,1);
		board.removePiece(coord1);
		coord1 = new Coordinate(3,6);
		board.removePiece(coord1);
		
		// Move White Queen
		coord1 = new Coordinate(4,0);
		coord2 = new Coordinate(3,1);
		board.movePiece(coord1, coord2);
		coord1 = new Coordinate(3,5);
		board.movePiece(coord2, coord1);

		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckmateBlack() {
		Board board = new Board(
				"rrrrrrrrxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxKxxxx");
		assertTrue(board.isInCheckmate(Color.BLACK));
	}
}
