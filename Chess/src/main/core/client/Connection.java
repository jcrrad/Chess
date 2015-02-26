package core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Connection {
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Gson gson;

	public Connection(String hostName, int port) throws UnknownHostException, IOException
	{
		this.socket = new Socket(hostName, port);
		//this.out = new PrintWriter( socket.getOutputStream(), true);
		//this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new ObjectOutputStream( socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
		this.gson = new Gson();
	}

	public void send(Message obj)
	{
		//serialize obj
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Message receive() {
		Message message = null;
		try {
			message = (Message)in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
