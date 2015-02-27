package core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

public class Connection {
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Gson gson;

	public Connection(String hostName, int port) throws UnknownHostException, IOException
	{
		this.socket = new Socket(hostName, port);
		this.out = new PrintWriter( socket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.gson = new Gson();
	}

	public void send(Message obj)
	{
		//serialize obj
		out.println(gson.toJson(obj));
		out.flush();
	}

	public Message receive() {
		try {
			Message message = gson.fromJson(in.readLine(), Message.class);
			return message;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
