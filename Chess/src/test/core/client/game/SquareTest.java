package core.client.game;

import java.awt.Color;

import gui.Square;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Base test class for {@link Square}
 * 
 * @author Brisbin
 *
 */
public class SquareTest {
	@Test
	public void testGetRow() {
		Square square = new Square(0, 1, Color.BLACK);
		square.setRow(2);
		assertEquals(square.getRow(), 2);
	}

	@Test
	public void testGetCol() {
		Square square = new Square(0, 1, Color.BLACK);
		square.setColumn(5);
		assertEquals(square.getColumn(), 5);
	}
}
