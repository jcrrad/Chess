package core.client;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import core.client.game.Board;

public class ClientTest {

	@Test
	public void testCilent() {
		try {
			Client client = new Client("localhost", 8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMessage() {
		try {
			Connection conn = new Connection("localhost", 8000);
			Message message = new Message();
			message.setBoard(new Board().toString());
			message.setChatText("this is the message");
			conn.send(message);
			conn.receive();
			message.setStalemate(false);
			message.setClientsTurn(true);
			message.isClientsTurn();
			message.getBoard();
			message.getUsername();
			message.setUsername("new name");
			message.setDisconnected(false);
			assertEquals(message.getChatText(), "this is the message");
			assertEquals(message.getUsername(), "new name");
			assertTrue(message.isClientsTurn());
			assertTrue(message.hasChat());
			assertTrue(message.hasBoardUpdate());
			assertTrue(message.hasReconnected());
			assertFalse(message.isStalemate());
			assertFalse(message.isDisconnected());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
