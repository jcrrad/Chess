package core.client.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;

/**
 * Base test class for {@link Knight}
 * @author Brisbin
 *
 */
public class KnightTest 
{

	@Test
	public void testGetSymbol_black()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Knight knight = new Knight(null, Color.BLACK, coord);
		assertEquals("N", knight.getSymbol());
	}
	
	@Test
	public void testGetSymbol_white()
	{
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Knight knight = new Knight(null, Color.WHITE, coord);
		assertEquals("n", knight.getSymbol());
	}
	
	@Test
	public void testCanMove_oneSquareDiag()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(3);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(4);
		assertFalse(knight.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_oneSquareUp_twoRight()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(3);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(5);
		assertTrue(knight.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_twoSquaresUp_oneRight()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(3);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(5);
		moveTo.setY(4);
		assertTrue(knight.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_twoSquaresHoriz()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(3);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(5);
		assertFalse(knight.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_twoSquaresVert()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(5);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(5);
		moveTo.setY(3);
		assertFalse(knight.canMove(moveTo));
	}
	
	@Test
	public void testCanMove_noSquares()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(5);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(3);
		moveTo.setY(3);
		assertFalse(knight.canMove(moveTo));
	}
	
	@Test
	public void testMoveable_invalidMove()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(3);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(4);
		assertFalse(knight.moveable(moveTo));
	}
	
	@Test
	public void testMoveable_validMove()
	{
		Coordinate knightLoc = new Coordinate();
		knightLoc.setX(3);
		knightLoc.setY(3);
		Knight knight = new Knight(null, Color.BLACK, knightLoc);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(5);
		assertTrue(knight.moveable(moveTo));
	}
}
