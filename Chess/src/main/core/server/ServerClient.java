package core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.google.gson.GsonBuilder;

public class ServerClient {

	public Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ServerClient(Socket clientSocket) throws IOException {
		this.socket = clientSocket;
		setOutputStream(socket.getOutputStream());
		setInputStream(socket.getInputStream());
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
	
	public void setOutputStream(OutputStream out)
	{
		this.out = new PrintWriter(out, true);
	}
	
	public void setInputStream(InputStream in)
	{
		this.in = new BufferedReader( new InputStreamReader(in));		
	}
	
	public InputStream getInputStream() throws IOException {
		return socket.getInputStream();
	}
	
	public OutputStream getOutputStream() throws IOException
	{
		return socket.getOutputStream();
	}
}
