package core.client.game;

import static org.junit.Assert.*;

import org.junit.Test;

import core.client.game.EmptyPiece;

public class EmptyPieceTypeTest 
{
	@Test
	public void testGetSymbol()
	{
		EmptyPiece piece = new EmptyPiece(null, null, null);
		assertEquals("x", piece.getSymbol());
	}
	
	@Test
	public void testCanMove()
	{
		EmptyPiece piece = new EmptyPiece(null, null, null);
		assertFalse(piece.canMove(null));
	}
}
