package gui;

import static org.junit.Assert.*;

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
		// move pawn
		Coordinate old = new Coordinate(3, 1);
		Coordinate newer = new Coordinate(4, 2);
		board.movePiece(old, newer);
		// move pawn
		old = new Coordinate(3, 6);
		newer = new Coordinate(4, 5);
		board.movePiece(old, newer);

		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckBlackKingMoves() {
		Board board = new Board();
		// move pawn
		Coordinate old = new Coordinate(3, 1);
		Coordinate newer = new Coordinate(4, 2);
		board.movePiece(old, newer);
		// move pawn
		old = new Coordinate(3, 6);
		newer = new Coordinate(4, 5);
		board.movePiece(old, newer);

		// move queen into place to kill
		old = new Coordinate(3, 0);
		newer = new Coordinate(3, 6);
		board.movePiece(old, newer);
		// king can kill queen

		System.out.println(board.toString());

		assertTrue(board.isInCheck(Color.BLACK));
		assertFalse(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckBlackKillAssassin() {
		Board board = new Board();
		// move pawn
		Coordinate old = new Coordinate(3, 1);
		Coordinate newer = new Coordinate(4, 2);
		board.movePiece(old, newer);
		// move pawn
		old = new Coordinate(3, 6);
		newer = new Coordinate(4, 5);
		board.movePiece(old, newer);

		// move queen into place to kill
		old = new Coordinate(3, 0);
		newer = new Coordinate(3, 5);
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
