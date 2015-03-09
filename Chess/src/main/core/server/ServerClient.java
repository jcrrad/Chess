package core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import core.client.Message;

public class ServerClient implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in;
	private GameServer game;
	
	public ServerClient(Socket clientSocket) throws IOException {
		this.socket = clientSocket;
		setInputStream(socket.getInputStream());
		this.out = new PrintWriter(socket.getOutputStream());
	}
	
	private void setInputStream(InputStream in)
	{
		this.in = new BufferedReader( new InputStreamReader(in));		
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
		this.out.println(msg);
		this.out.flush();
	}
	
	public void send(Message msg)
	{
		Gson gson = new Gson();
		this.send(gson.toJson(msg));
	}
	
	public void connect(ServerClient opponent) throws IOException
	{
		this.out = new PrintWriter(opponent.getOutputStream());
	}

	public void disconnect()
	{
		Message msg = new Message();
		msg.setDisconnected(true);
		this.send(msg);
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() 
	{
		try {
			communicate();
		} catch (IOException e) {
			this.game.disconnection();
			this.disconnect();
		}
	}

	public void setGame(GameServer game)
	{
		this.game = game;
	}
	
	private void communicate() throws IOException 
	{
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
	        out.println(inputLine);
	        out.flush();
	    }
	}
}
