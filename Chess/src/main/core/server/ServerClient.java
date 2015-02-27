package core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.GsonBuilder;

public class ServerClient implements Runnable {

	private Socket socket;
	private ObjectOutputStream out = null;
	private ObjectInputStream in;
	
	public ServerClient(Socket clientSocket) throws IOException {
		this.socket = clientSocket;
		setInputStream(socket.getInputStream());
		//this.out = new PrintWriter(socket.getOutputStream());
		this.out = new ObjectOutputStream( socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	private void setInputStream(InputStream in)
	{
		try {
			this.in = new ObjectInputStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
		//this.out.println(msg);
		//this.out.flush();
	}
	
	public void connect(ServerClient opponent) throws IOException
	{
		this.out = new ObjectOutputStream(opponent.getOutputStream());
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
			e.printStackTrace();
		}
	}

	private void communicate() throws IOException 
	{
		Object inputLine;
		try {
			while ((inputLine = in.readObject()) != null) {
			    out.writeObject(inputLine);
			    out.flush();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
