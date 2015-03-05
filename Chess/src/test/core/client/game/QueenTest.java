package core.client.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;

/**
 * Base test class for {@link Queen}
 * @author Brisbin
 *
 */
public class QueenTest 
{
	@Test
	public void testGetSymbol_black()
	{
		Queen queen = new Queen(null, Color.BLACK, null);
		assertEquals("Q", queen.getSymbol());
	}
	
	@Test
	public void testGetSymbol_white()
	{
		Queen queen = new Queen(null, Color.WHITE, null);
		assertEquals("q", queen.getSymbol());
	}
	
	@Test
	public void testCanMove_invalidMove()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(7);
		assertFalse(queen.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_validDiagMove()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(6);
		moveTo.setY(6);
		assertTrue(queen.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_validMoveHoriz()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(4);
		assertTrue(queen.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_validMoveVert()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(3);
		assertTrue(queen.canMove(moveTo));
	}
}

