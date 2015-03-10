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
		// Remove pawn
		Coordinate old = new Coordinate(3, 1);
		board.removePiece(old);
		// Remove pawn
		old = new Coordinate(3, 6);
		board.removePiece(old);
		
		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckBlackKingMoves() {
		Board board = new Board();
		// Remove first pawn
		Coordinate loc = new Coordinate(3, 1);
		board.removePiece(loc);

		// Remove second pawn
		loc = new Coordinate(3, 6);
		board.removePiece(loc);

		// move queen into place to kill
		loc = new Coordinate(3, 0);
		Coordinate loc2 = new Coordinate(3, 6);
		board.movePiece(loc, loc2);
		// king can kill queen

		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckBlackKillAssassin() {
		Board board = new Board();
		// Remove pawn
		Coordinate old = new Coordinate(3, 1);
		board.removePiece(old);
		// move pawn
		old = new Coordinate(3, 6);
		board.removePiece(old);

		// move queen into place to kill
		old = new Coordinate(3, 0);
		Coordinate newer = new Coordinate(3, 5);
		// pawn can kill queen
		board.movePiece(old, newer);
		
		
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
