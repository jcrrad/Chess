package core.client.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import core.client.Coordinate;
import core.client.game.Piece;

/**
 * Base test class for {@link Piece}
 * 
 * @author Brisbin
 *
 */
public class PieceTest {
	@Test
	public void testSetLocation() {
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(3);
		queen.setLocation(moveTo);
		assertEquals(queen.getLocation(), moveTo);
	}

	@Test
	public void testGetLocation() {
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		assertEquals(queen.getLocation(), coord);
	}

	@Test
	public void testHasMovedFalse() {
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		assertFalse(queen.hasMoved());
	}

	@Test
	public void testHasMoved() {
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		Coordinate moveTo = new Coordinate();
		moveTo.setX(4);
		moveTo.setY(3);
		queen.setLocation(moveTo);
		assertEquals(queen.getLocation(), moveTo);
		assertTrue(queen.hasMoved());
	}

	@Test
	public void testBlack() {
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.BLACK, coord);
		assertEquals(queen.getColor(), Color.BLACK);
	}

	@Test
	public void testWhite() {
		Coordinate coord = new Coordinate();
		coord.setX(3);
		coord.setY(3);
		Queen queen = new Queen(null, Color.WHITE, coord);
		assertEquals(queen.getColor(), Color.WHITE);
	}
}
