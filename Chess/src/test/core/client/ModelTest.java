package core.client;

import static org.junit.Assert.*;

import org.junit.Test;

import core.client.Model;
import core.client.Model.STATE;
import core.client.game.Board;

public class ModelTest {

	@Test
	public void testGetConnection()
	{
		Model model = new Model(null ,8080);
		assertTrue(model.getConnection() == null);
	}
	
	@Test
	public void testGetState()
	{
		Model model = new Model(null, 8080);
		assertTrue(model.getState().equals(STATE.LOGIN));
	}
	
	@Test
	public void testSetState()
	{
		Model model = new Model(null, 8080);
		model.setState(STATE.INGAME);
		assertTrue(model.getState().equals(STATE.INGAME));
	}
	
	@Test
	public void testGetProductInformation()
	{
		Model model = new Model(null, 8080);
		assertTrue(model.getProductInformation() != null);
	}
	
	@Test
	public void testGetUsername()
	{
		Model model = new Model(null, 8080);
		assertTrue(model.getUsername() == null);
	}
	
	@Test
	public void testSetUsername()
	{
		Model model = new Model(null, 8080);
		model.setUsername("username");
		assertTrue(model.getUsername().equals("username"));
	}
	
	@Test
	public void testGetServerHostname()
	{
		Model model = new Model("serverHostname", 8080);
		assertTrue(model.getServerHostname().equals("serverHostname"));
	}
	
	@Test
	public void testGetServerPort()
	{
		Model model = new Model(null, 8080);
		assertTrue(model.getServerPort() == 8080);
	}
	
	@Test
	public void testGetBoard()
	{
		Model model = new Model(null, 8080);
		assertTrue(model.getBoard() != null);
	}
	
	@Test
	public void testSetBoard()
	{
		Model model = new Model(null, 8080);
		Board newBoard = new Board();
		model.setBoard(newBoard);
		assertTrue(model.getBoard() == newBoard);
	}
}
