package core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.GsonBuilder;

public class ServerClient {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ServerClient(Socket clientSocket) throws IOException {
		this.socket = clientSocket;
		this.out = new PrintWriter(clientSocket.getOutputStream(), true);
		this.in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public String getIPAddress()
	{
		return this.socket.getInetAddress().getHostAddress();
	}
	
	public void send(String msg)
	{
		this.out.println(msg);
	}

	public void disconnect() throws IOException 
	{
		this.socket.close();
	}
}
