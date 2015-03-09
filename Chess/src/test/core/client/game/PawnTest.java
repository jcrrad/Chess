package core.client.game;

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;
import core.client.Coordinate;
import core.client.game.Pawn;

/**
 * Base test class for {@link Pawn}
 * 
 * @author Brisbin
 *
 */
public class PawnTest {
	@Test
	public void testGetSymbol_black() {
		Pawn pawn = new Pawn(null, Color.BLACK, null);
		assertEquals("P", pawn.getSymbol());
	}

	@Test
	public void testGetSymbol_white() {
		Pawn pawn = new Pawn(null, Color.WHITE, null);
		assertEquals("p", pawn.getSymbol());
	}

	@Test
	public void testCanMove_validWhiteDoubleJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,1);
		Coordinate moveTo = new Coordinate(3,3);
		Pawn pawn = (Pawn) board.getPiece(coord);
		assertTrue(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_invalidWhiteDoubleJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,2);
		Coordinate moveTo = new Coordinate(3,4);
		Pawn pawn = new Pawn(board, Color.WHITE, coord);
		board.setPiece(pawn, coord);
		assertFalse(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_validBlackDoubleJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,6);
		Coordinate moveTo = new Coordinate(3,4);
		Pawn pawn = (Pawn) board.getPiece(coord);
		assertTrue(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_invalidBlackDoubleJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,5);
		Coordinate moveTo = new Coordinate(3,3);
		Pawn pawn = new Pawn(board, Color.BLACK, coord);
		board.setPiece(pawn, coord);
		assertFalse(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_validWhiteJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,1);
		Coordinate moveTo = new Coordinate(3,2);
		Pawn pawn = (Pawn) board.getPiece(coord);
		assertTrue(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_invalidWhiteJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,3);
		Coordinate moveTo = new Coordinate(3,2);
		Pawn pawn = new Pawn(board, Color.WHITE, coord);
		board.setPiece(pawn, coord);
		assertFalse(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_validBlackJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,6);
		Coordinate moveTo = new Coordinate(3,5);
		Pawn pawn = (Pawn) board.getPiece(coord);
		assertTrue(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_invalidBlackJump() {
		Board board = new Board();
		Coordinate coord = new Coordinate(3,4);
		Coordinate moveTo = new Coordinate(3,5);
		Pawn pawn = new Pawn(board, Color.BLACK, coord);
		board.setPiece(pawn, coord);
		assertFalse(pawn.canMove(moveTo));
	}

	@Test
	public void testCanAttack_validWhite() {
		Board board = new Board();
		Coordinate coord1 = new Coordinate(3,3);
		Coordinate coord2 = new Coordinate(2,4);
		Pawn pawn = new Pawn(board, Color.WHITE, coord1);
		board.setPiece(pawn, coord1);
		Pawn enemy = new Pawn(board, Color.BLACK, coord2);
		board.setPiece(enemy, coord2);
		assertTrue(pawn.canAttack(coord2));
	}

	@Test
	public void testCanAttack_invalidWhite() {
		Board board = new Board();
		Coordinate coord1 = new Coordinate(3,3);
		Coordinate coord2 = new Coordinate(3,4);
		Coordinate coord3 = new Coordinate(2,4);
		Coordinate coord4 = new Coordinate(2,2);
		Coordinate coord5 = new Coordinate(4,4);
		Pawn pawn = new Pawn(board, Color.WHITE, coord1);
		board.setPiece(pawn, coord1);
		Pawn enemy = new Pawn(board, Color.BLACK, coord2);
		board.setPiece(enemy, coord2);
		Pawn friend = new Pawn(board, Color.WHITE, coord3);
		board.setPiece(friend, coord3);
		Pawn enemy2 = new Pawn(board, Color.BLACK, coord4);
		board.setPiece(enemy2, coord4);
		assertFalse(pawn.canAttack(coord2));
		assertFalse(pawn.canAttack(coord3));
		assertFalse(pawn.canAttack(coord4));
		assertFalse(pawn.canAttack(coord5));
	}

	@Test
	public void testCanAttack_validBlack() {
		Board board = new Board();
		Coordinate coord1 = new Coordinate(3,4);
		Coordinate coord2 = new Coordinate(2,3);
		Pawn pawn = new Pawn(board, Color.BLACK, coord1);
		board.setPiece(pawn, coord1);
		Pawn enemy = new Pawn(board, Color.WHITE, coord2);
		board.setPiece(enemy, coord2);
		assertTrue(pawn.canAttack(coord2));
	}

	@Test
	public void testCanAttack_invalidBlack() {
		Board board = new Board();
		Coordinate coord1 = new Coordinate(3,4);
		Coordinate coord2 = new Coordinate(3,3);
		Coordinate coord3 = new Coordinate(2,3);
		Coordinate coord4 = new Coordinate(4,5);
		Coordinate coord5 = new Coordinate(4,3);
		Pawn pawn = new Pawn(board, Color.BLACK, coord1);
		board.setPiece(pawn, coord1);
		Pawn enemy = new Pawn(board, Color.WHITE, coord2);
		board.setPiece(enemy, coord2);
		Pawn friend = new Pawn(board, Color.BLACK, coord3);
		board.setPiece(friend, coord3);
		Pawn enemy2 = new Pawn(board, Color.WHITE, coord4);
		board.setPiece(enemy2, coord4);
		assertFalse(pawn.canAttack(coord2));
		assertFalse(pawn.canAttack(coord3));
		assertFalse(pawn.canAttack(coord4));
		assertFalse(pawn.canAttack(coord5));
	}

}