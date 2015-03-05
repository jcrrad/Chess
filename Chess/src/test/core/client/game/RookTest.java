package core.client.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;

/**
 * Base test class for {@link Rook}
 * @author Brisbin
 *
 */
public class RookTest 
{
	@Test
	public void testGetSymbol_black()
	{
		Rook rook = new Rook(null, Color.BLACK, null);
		assertEquals("R", rook.getSymbol());
	}
	
	@Test
	public void testGetSymbol_white()
	{
		Rook rook = new Rook(null, Color.WHITE, null);
		assertEquals("r", rook.getSymbol());
	}
	
	@Test
	public void testCanMove_validMoveHoriz()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Rook rook = new Rook(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(4);
		assertTrue(rook.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_validMoveVert()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Rook rook = new Rook(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(3);
		assertTrue(rook.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_invalidMove()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Rook rook = new Rook(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(4);
		assertFalse(rook.canMove(moveTo));
	}
}
