package core.server;

import java.io.IOException;
import core.client.Message;

enum STATE {
	INGAME, GAMEOVER
}

public class GameServer{

	private ServerClient client1;
	private ServerClient client2;
	STATE state = STATE.INGAME;
	
	public GameServer(Pair<?, ?> pair) 
	{
		client1 = (ServerClient)pair.client1;
		client2 = (ServerClient)pair.client2;
		client1.setGame(this);
		client2.setGame(this);
		connectUsers();
		handShake();
	}


	private void handShake() 
	{
		Message message = new Message();
		
		message.setUsername("Server");
		message.setClientsTurn(true);
		message.setChatText("Welcome to the game, enjoy. You are first player, and your current piece color is black.");
		 
		client1.send(message);
		
		message.setClientsTurn(false);
		message.setChatText("Welcome to the game, enjoy. Your piece color is white.");
		client2.send(message);
	}


	private void connectUsers() 
	{
		
		try {
			client1.connect(client2);
			client2.connect(client1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(client1).start();
		new Thread(client2).start();
	}


	public void disconnection() 
	{		
		if(this.state == STATE.GAMEOVER)
			return;
		
		this.state = STATE.GAMEOVER;
	}
	
	public STATE getState()
	{
		return this.state;
	}
}




