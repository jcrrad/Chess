package core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Defines a server that when the run method is called, listens for incoming
// clients. Accepting connections to hand off to ClientWorker threads.
public class Server {

	private int maxClients;
	private ServerSocket socket;
	private Sommelier pairingAgent;
	
	public Server(int newPort) throws IOException
	{
		this.maxClients = 20;
		this.socket = new ServerSocket(newPort);
		this.pairingAgent = new Sommelier(new RandomPool(30));
		new Thread(this.pairingAgent).start();
	}
	
	public Server(int newPort, int numClients) throws IOException 
	{
		this.socket = new ServerSocket(newPort);
		this.maxClients = numClients;
		this.pairingAgent = new Sommelier(new RandomPool(30));
	}
	
	/**
	 * Starts listening to a socket for incoming connections.
	 * @throws IOException 
	 */
	private void run() throws IOException 
	{
		//Busy loop when thread.activeCount >=this.maxClients
		while(true)
		{
			System.out.println("Waiting for next client");
			Socket clientSocket = this.socket.accept();
			System.out.println("Someone has connected!");
			new Thread(new ClientWorker(this.pairingAgent, clientSocket)).start();
		}
	}
	  
	public static void main( String[] args) throws IOException
	{
		final int MAXCLIENTS = 200; 
		Server server = new Server(8000);
		// Throws IOException
		server.run();
	}
}
