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
public class BoardCheck {
	@Test
	public void testMoveBlank() {

		Board board = new Board();
		Coordinate old = new Coordinate(4, 4);
		Coordinate newer = new Coordinate(5, 5);
		board.movePiece(old, newer);
	}

	@Test
	public void testMoveBlacnk() {

		Board old = new Board();
		System.out.println(old.toString());
		Board board = new Board(old.toString());
		System.out.println(board.toString());
		assertEquals(old.toString(), board.toString());
	}

}
