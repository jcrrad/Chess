package gui;

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
		assertEquals(square.getRow(), 1);
	}

	@Test
	public void testGetCol() {
		Square square = new Square(0, 1, Color.BLACK);
		assertEquals(square.getColumn(), 0);
	}
}
