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

		// move pawn
		Coordinate old = new Coordinate(4, 1);
		Coordinate newer = new Coordinate(5, 2);
		board.movePiece(old, newer);

		// move pawn
		old = new Coordinate(4, 6);
		newer = new Coordinate(5, 5);
		board.movePiece(old, newer);

		board.movePiece(old, newer);

		assertFalse(board.isInCheck(Color.BLACK));
		assertTrue(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckWhiteKingMoves() {
		Board board = new Board();

		// move pawn
		Coordinate old = new Coordinate(4, 1);
		Coordinate newer = new Coordinate(5, 2);
		board.movePiece(old, newer);

		// move pawn
		old = new Coordinate(4, 6);
		newer = new Coordinate(5, 5);
		board.movePiece(old, newer);

		// move queen into place to kill
		old = new Coordinate(4, 7);
		newer = new Coordinate(4, 1);
		// king kill queen
		board.movePiece(old, newer);

		assertFalse(board.isInCheck(Color.BLACK));
		assertTrue(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

	@Test
	public void testCheckWhiteKillAssassin() {
		Board board = new Board();

		// move pawn
		Coordinate old = new Coordinate(4, 1);
		Coordinate newer = new Coordinate(5, 2);
		board.movePiece(old, newer);

		// move pawn
		old = new Coordinate(4, 6);
		newer = new Coordinate(5, 5);
		board.movePiece(old, newer);

		// move queen into place to kill
		old = new Coordinate(4, 7);
		newer = new Coordinate(4, 2);
		// pawn kill queen
		board.movePiece(old, newer);

		assertFalse(board.isInCheck(Color.BLACK));
		assertTrue(board.isInCheck(Color.WHITE));
		assertFalse(board.isInCheckmate(Color.BLACK));
		assertFalse(board.isInCheckmate(Color.WHITE));
	}

}
