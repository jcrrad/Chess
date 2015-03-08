package core.client.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
