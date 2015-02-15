package core.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Assists the server in accepting connections
public class ClientWorker implements Runnable {
	
	private Socket clientSocket;
	private Sommelier pairingAgent;

	public ClientWorker(Sommelier pairingAgent, Socket newClient)
	{
		this.clientSocket = newClient;
		this.pairingAgent = pairingAgent;
	}

	@Override
	public void run() 
	{
		try
		{
			ServerClient client = new ServerClient(clientSocket);
			this.pairingAgent.register(client);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
