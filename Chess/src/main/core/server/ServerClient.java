package core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.client.Message;

public class ServerClient implements Runnable {

	private GameServer game;
	public Socket socket;
	private PrintWriter toClient = null;
	private BufferedReader fromClient;
	private BufferedReader fromOpponent;
	
	public ServerClient(Socket clientSocket) throws IOException {
		this.socket = clientSocket;
		setInputStream(socket.getInputStream());
		this.toClient = new PrintWriter(socket.getOutputStream());
		//this.fromClient = new BufferedReader( new InputStreamReader(in));
	}
	
	private void setInputStream(InputStream in)
	{
		this.fromOpponent = new BufferedReader( new InputStreamReader(in));		
	}
	
	public String getIPAddress()
	{
		return this.socket.getInetAddress().getHostAddress();
	}
	
	public OutputStream getOutputStream() throws IOException
	{
		return this.socket.getOutputStream();
	}
	
	public void send(String msg)
	{
		this.toClient.println(msg);
		this.toClient.flush();
	}
	
	public void connect(ServerClient opponent) throws IOException
	{
		this.toClient = new PrintWriter(opponent.getOutputStream());
	}

	public void disconnect() throws IOException 
	{
		this.socket.close();
	}

	@Override
	public void run() 
	{
		try {
			communicate();
		} catch (IOException e) {
			System.out.println("Someone disconnected");
			sendDisconnectionNotice();
			this.game.playerDisconnect(this);
		}
	}

	private void sendDisconnectionNotice() 
	{
		Gson gson = new Gson();
		Message message = new Message();
		message.setDisconnected(true);
		toClient.println(gson.toJson(message));
		toClient.flush();
	}

	private void communicate() throws IOException 
	{
		String inputLine;
		while ((inputLine = fromOpponent.readLine()) != null) {
	        toClient.println(inputLine);
	        toClient.flush();
	    }
	}

	public void setGame(GameServer gameServer) 
	{
		this.game = gameServer;
	}
}
