package core.server;

import java.io.IOException;

import com.google.gson.Gson;

import core.client.Message;

public class GameServer{

	private Pair<?, ?> pair;
	public GameServer(Pair<?, ?> pair) 
	{
		this.pair = pair;
		connectUsers();
		handShake();
	}


	private void handShake() 
	{
		Gson gson = new Gson();
		Message message = new Message();
		
		message.setUsername("Server");
		message.setClientsTurn(true);
		message.setChatText("Welcome to the game, enjoy. You First.");
		 
		((ServerClient) this.pair.client1).send(gson.toJson(message));
		
		message.setClientsTurn(false);
		message.setChatText("Welcome to the game, enjoy.");
		((ServerClient) this.pair.client2).send(gson.toJson(message));
	}


	private void connectUsers() 
	{
		
		ServerClient c1 = (ServerClient)pair.client1;
		ServerClient c2 = (ServerClient)pair.client2;
		try {
			c1.connect(c2);
			c2.connect(c1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(c1).start();
		new Thread(c2).start();
	}
}




