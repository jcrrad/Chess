package core.client.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;
import core.client.game.Bishop;

/**
 * Base test class for {@link Bishop}
 * @author Brisbin
 *
 */
public class BishopTest 
{
	@Test
	public void testGetSymbol_black()
	{
		Bishop bishop = new Bishop(null, Color.BLACK, null);
		assertEquals("B", bishop.getSymbol());
	}
	
	@Test
	public void testGetSymbol_white()
	{
		Bishop bishop = new Bishop(null, Color.WHITE, null);
		assertEquals("b", bishop.getSymbol());
	}
	
	@Test
	public void testCanMove_validDiagMove()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Bishop bishop = new Bishop(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(5);
		moveTo.setY(5);
		assertTrue(bishop.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_invalidMove()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Bishop bishop = new Bishop(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(6);
		moveTo.setY(4);
		assertFalse(bishop.canMove(moveTo));
	}
}
