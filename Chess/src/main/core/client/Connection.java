package core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

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
		Message message = gson.fromJson(in, Message.class);
		return message;
	}

}