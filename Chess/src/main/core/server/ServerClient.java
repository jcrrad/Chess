package core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClient implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in;
	
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
	
	public void connect(ServerClient opponent) throws IOException
	{
		this.out = new PrintWriter(opponent.getOutputStream());
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
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
	        out.println(inputLine);
	        out.flush();
	    }
	}
}
