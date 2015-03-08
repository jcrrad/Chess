package core.client.game;

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;
import core.client.Coordinate;
import core.client.game.Pawn;

/**
 * Base test class for {@link Pawn}
 * @author Brisbin
 *
 */
public class PawnTest 
{
	@Test
	public void testGetSymbol_black()
	{
		Pawn pawn = new Pawn(null, Color.BLACK, null);
		assertEquals("P", pawn.getSymbol());
	}
	
	@Test
	public void testGetSymbol_white()
	{
		Pawn pawn = new Pawn(null, Color.WHITE, null);
		assertEquals("p", pawn.getSymbol());
	}
	
	@Test
	public void testCanMove_validWhiteDoubleJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(1);;
		Pawn pawn = new Pawn(null, Color.WHITE, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(3);
		assertTrue(pawn.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_invalidWhiteDoubleJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(2);;
		Pawn pawn = new Pawn(null, Color.WHITE, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(4);
		assertFalse(pawn.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_validBlackDoubleJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(6);;
		Pawn pawn = new Pawn(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(4);
		assertTrue(pawn.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_invalidBlackDoubleJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(5);;
		Pawn pawn = new Pawn(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(3);
		assertFalse(pawn.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_validWhiteJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(1);
		Pawn pawn = new Pawn(null, Color.WHITE, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(2);
		assertTrue(pawn.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_invalidWhiteJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(1);
		Pawn pawn = new Pawn(null, Color.WHITE, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(0);
		assertFalse(pawn.canMove(moveTo));
	}

	@Test
	public void testCanMove_validBlackJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(6);
		Pawn pawn = new Pawn(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(5);
		assertTrue(pawn.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_invalidBlackJump()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(6);
		Pawn pawn = new Pawn(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(7);
		assertFalse(pawn.canMove(moveTo));
	}

	@Test
	public void testCanAttack_validWhite()
	{
		Board board = new Board();
		Coordinate coord1 = new Coordinate();
		coord1.setX(3);
		coord1.setY(3);
		Pawn pawn = new Pawn(board, Color.WHITE, coord1);
		board.setPiece(pawn, coord1);
		Coordinate coord2 = new Coordinate();
		coord2.setX(2);
		coord2.setY(4);
		Pawn enemy = new Pawn(board, Color.BLACK, coord2);
		board.setPiece(enemy, coord2);
		assertTrue(pawn.canAttack(coord2));
	}
	
	@Test
	public void testCanAttack_invalidWhite()
	{
		Board board = new Board();
		Coordinate coord1 = new Coordinate();
		coord1.setX(3);
		coord1.setY(3);
		Pawn pawn = new Pawn(board, Color.WHITE, coord1);
		board.setPiece(pawn, coord1);
		Coordinate coord2 = new Coordinate();
		coord2.setX(3);
		coord2.setY(4);
		Pawn enemy = new Pawn(board, Color.BLACK, coord2);
		board.setPiece(enemy, coord2);
		assertFalse(pawn.canAttack(coord2));
	}
	
	@Test
	public void testCanAttack_validBlack()
	{
		Board board = new Board();
		Coordinate coord1 = new Coordinate();
		coord1.setX(3);
		coord1.setY(4);
		Pawn pawn = new Pawn(board, Color.BLACK, coord1);
		board.setPiece(pawn, coord1);
		Coordinate coord2 = new Coordinate();
		coord2.setX(2);
		coord2.setY(3);
		Pawn enemy = new Pawn(board, Color.WHITE, coord2);
		board.setPiece(enemy, coord2);
		assertTrue(pawn.canAttack(coord2));
	}
	
	@Test
	public void testCanAttack_invalidBlack()
	{
		Board board = new Board();
		Coordinate coord1 = new Coordinate();
		coord1.setX(3);
		coord1.setY(4);
		Pawn pawn = new Pawn(board, Color.BLACK, coord1);
		board.setPiece(pawn, coord1);
		Coordinate coord2 = new Coordinate();
		coord2.setX(3);
		coord2.setY(3);
		Pawn enemy = new Pawn(board, Color.WHITE, coord2);
		board.setPiece(enemy, coord2);
		assertFalse(pawn.canAttack(coord2));
	}
	
}
