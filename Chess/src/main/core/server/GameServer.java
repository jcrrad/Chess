package core.server;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;

import core.client.Message;

public class GameServer{

	private Pair pair;
	private String state;
	
	public GameServer(Pair pair) 
	{
		this.pair = pair;
		connectUsers();
		handShake();
	}


	private void handShake() 
	{
		System.out.println("Handshakes");
		Gson gson = new Gson();
		Message message = new Message();
		
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
		System.out.println("Connecting users");
		try {
			c1.connect(c2);
			c2.connect(c1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Creating threads");
		new Thread(c1).start();
		new Thread(c2).start();
	}
}




