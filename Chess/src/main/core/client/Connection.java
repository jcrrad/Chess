package core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Connection(String hostName, int port) throws UnknownHostException, IOException
	{
		this.socket = new Socket(hostName, port);
		this.out = new PrintWriter(socket.getOutputStream(), true);
		this.in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
	}

	void send(String message)
	{
		out.println(message);
		out.flush();
	}

	public String receive() throws IOException {
		return in.readLine();
	}

}
