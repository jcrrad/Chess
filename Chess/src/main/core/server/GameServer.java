package core.server;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;

import core.client.Message;

public class GameServer{

	public enum STATE {
		INGAME, PAUSED
	}
	
	private Pair pair;
	public STATE state = STATE.INGAME;
	private String disconnectedUserIP;
	private ServerClient client1;
	private ServerClient client2;
	
	public GameServer(Pair pair) 
	{
		this.pair = pair;
		client1 = (ServerClient)pair.client1;
		client2 = (ServerClient)pair.client2;
		client1.setGame(this);
		client2.setGame(this);
		
		connectUsers();
		
		handShake();
	}
	
	private boolean connectUsers()
	{
		try {
			connectUser(client1, client2);
			connectUser(client2, client1);
		} catch (IOException e) {
			System.out.println("Could not connect users. Disconnecting both");
			try {
				client1.disconnect();
				client2.disconnect();
				return false;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}

	private void handShake() 
	{
		System.out.println("Handshakes");
		Gson gson = new Gson();
		Message message = new Message();
		
		message.setClientsTurn(true);
		message.setChatText("Welcome to the game, enjoy.");
		 
		((ServerClient) this.pair.client1).send(gson.toJson(message));
		
		message.setClientsTurn(false);
		((ServerClient) this.pair.client2).send(gson.toJson(message));
	}
	
	private void connectUser(ServerClient client, ServerClient to) throws IOException
	{
		client.connect(to);
		new Thread(client).start();
	}

	public void playerDisconnect(ServerClient remainingClient) 
	{
		ServerClient c = client2;
		
		if(client1 != remainingClient)
		{
			c = client1;
		}
		this.disconnectedUserIP = c.getIPAddress();
		this.state = STATE.PAUSED;
	}


	public boolean isDisconnectedClient(ServerClient c) 
	{
		return c.getIPAddress() == this.disconnectedUserIP;
	}


	public void reconnect(ServerClient c) 
	{
		Gson gson = new Gson();
		Message message = new Message();
		message.setDisconnected(false);

		if(this.client1.getIPAddress() == c.getIPAddress())
		{
			client1 = c;
			connectUsers();
			client1.send(gson.toJson(message));
		}
		else
		{
			client2 = c;
			connectUsers();
			client2.send(gson.toJson(message));
		}
	}
}
