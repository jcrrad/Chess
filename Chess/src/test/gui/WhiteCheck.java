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
public class WhiteCheck {
	@Test
	public void testCheckFalse() {

		Board board = new Board();
		assertFalse(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
	}

	@Test
	public void testCheckWhiteIntercept() {
		Board board = new Board();
		Coordinate coord1, coord2;

		// Remove pawns
		coord1 = new Coordinate(3,1);
		board.removePiece(coord1);
		coord1 = new Coordinate(3,6);
		board.removePiece(coord1);
		
		// Move Black Queen
		coord1 = new Coordinate(4,7);
		coord2 = new Coordinate(3,6);
		board.movePiece(coord1, coord2);
		
		assertFalse(board.isInCheck(Color.BLACK));
		assertTrue(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckWhiteKingMoves() {
		Board board = new Board();
		Coordinate coord1, coord2;
		
		// Remove pawns
		coord1 = new Coordinate(3,1);
		board.removePiece(coord1);
		coord1 = new Coordinate(3,6);
		board.removePiece(coord1);
		
		// Remove White Queen
		coord1 = new Coordinate(4,0);
		board.removePiece(coord1);

		// Move Black Queen
		coord1 = new Coordinate(4,7);
		coord2 = new Coordinate(3,6);
		board.movePiece(coord1, coord2);

		assertFalse(board.isInCheck(Color.BLACK));
		assertTrue(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckWhiteKillAssassin() {
		Board board = new Board();
		Coordinate coord1, coord2;

		// Remove pawns
		coord1 = new Coordinate(3,1);
		board.removePiece(coord1);
		coord1 = new Coordinate(3,6);
		board.removePiece(coord1);
		
		// Move Black Queen
		coord1 = new Coordinate(4,7);
		coord2 = new Coordinate(3,6);
		board.movePiece(coord1, coord2);
		coord1 = new Coordinate(3,2);
		board.movePiece(coord2, coord1);

		assertFalse(board.isInCheck(Color.BLACK));
		assertTrue(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckmateWhite() {
		Board board = new Board(
				"kxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxRRRRRRRR");
		assertTrue(board.isInCheckmate(Color.WHITE));
	}
}
