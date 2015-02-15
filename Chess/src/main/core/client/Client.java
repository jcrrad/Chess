package core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private PrintWriter out;
	BufferedReader in;
	public Socket serverSocket;

	public Client(String hostName, int port) throws UnknownHostException, IOException
	{
		this.serverSocket = new Socket(hostName, port);
		this.out = new PrintWriter(serverSocket.getOutputStream(), true);
		this.in = new BufferedReader( new InputStreamReader(serverSocket.getInputStream()));
	}
	
	void send(String message)
	{
		out.println(message);
	}

	public String receive() throws IOException {
		return in.readLine();
	}
}
