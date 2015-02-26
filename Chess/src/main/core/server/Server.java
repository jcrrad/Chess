package core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Defines a server that when the run method is called, listens for incoming
// clients. Accepting connections to hand off to ClientWorker threads.
public class Server {
	  
	public static void main( String[] args) throws IOException
	{
		final int MAXTHREADS = 4;
		final int PORT = 8000;
		
		Sommelier pairingAgent = new Sommelier(new RandomPool(30));
		Executor executor = Executors.newFixedThreadPool(MAXTHREADS);;
		ServerSocket listener = new ServerSocket(PORT);
		
		new Thread(pairingAgent).start();
		
		while(true)
		{
			System.out.println("Waiting for next client");
			Socket clientSocket = listener.accept();
			System.out.println("Someone has connected!");
			executor.execute(new ClientWorker(pairingAgent, clientSocket));
		}
	}
}
