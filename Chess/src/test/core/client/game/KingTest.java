package core.client.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;

/**
 * Base test class for {@link King}
 * @author Brisbin
 *
 */
public class KingTest 
{
	
	@Test
	public void testGetSymbol_black()
	{
		King king = new King(null, Color.BLACK, null);
		assertEquals("K", king.getSymbol());
	}
	
	@Test
	public void testGetSymbol_white()
	{
		King king = new King(null, Color.WHITE, null);;
		assertEquals("k", king.getSymbol());
	}
	
	@Test
	public void testCanMove_oneSquareVert()
	{
		Coordinate kingLoc = new Coordinate();
		kingLoc.setX(3);
		kingLoc.setY(3);
		King king = new King(null, Color.BLACK, kingLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(3);
		assertTrue(king.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_oneSquareHoriz()
	{
		Coordinate kingLoc = new Coordinate();
		kingLoc.setX(3);
		kingLoc.setY(3);
		King king = new King(null, Color.BLACK, kingLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(4);
		assertTrue(king.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_oneSquareDiag()
	{
		Coordinate kingLoc = new Coordinate();
		kingLoc.setX(3);
		kingLoc.setY(3);
		King king = new King(null, Color.BLACK, kingLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(4);
		assertTrue(king.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_twoSquaresVert()
	{
		Coordinate kingLoc = new Coordinate();
		kingLoc.setX(3);
		kingLoc.setY(3);
		King king = new King(null, Color.BLACK, kingLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(5);
		moveTo.setY(3);
		assertFalse(king.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_twoSquaresHoriz()
	{
		Coordinate kingLoc = new Coordinate();
		kingLoc.setX(3);
		kingLoc.setY(3);
		King king = new King(null, Color.BLACK, kingLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(5);
		assertFalse(king.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_twoSquaresDiag()
	{
		Coordinate kingLoc = new Coordinate();
		kingLoc.setX(3);
		kingLoc.setY(3);
		King king = new King(null, Color.BLACK, kingLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(5);
		moveTo.setY(5);
		assertFalse(king.canMove(moveTo));
	}

}
