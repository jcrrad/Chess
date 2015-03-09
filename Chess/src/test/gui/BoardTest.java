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
public class BoardTest {
	@Test
	public void testMoveBlank() {

		Board board = new Board();
		Coordinate old = new Coordinate(4, 4);
		Coordinate newer = new Coordinate(5, 5);
		board.movePiece(old, newer);
	}

	@Test
	public void testCopy() {
		Board old = new Board();
		Board board = new Board(old.toString());
		assertEquals(old.toString(), board.toString());
	}

	@Test
	public void testAttack() {
		Board board = new Board();
		Coordinate c1, c2;

		// move knight
		c1 = new Coordinate(1, 0);
		c2 = new Coordinate(2, 2);
		board.movePiece(c1, c2);

		c1 = new Coordinate(2, 2);
		c2 = new Coordinate(3, 4);
		board.movePiece(c1, c2);

		c1 = new Coordinate(3, 4);
		c2 = new Coordinate(4, 6);

		assertTrue(board.movePiece(c1, c2));
	}

}
