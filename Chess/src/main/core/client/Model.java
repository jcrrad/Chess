package core.client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Model {
	public static void main(String[] args) throws IOException
	{
		// Everything here can be factored out, I am just keeping it for testing purposes.
		// Feel free to correct me.
		Client client = null;
		try {
			client = new Client("localhost", 8000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Sending Message");
		if (client != null)
		{
			client.send("This is a message");
			client.send("this message 2");
		
			String fromServer;
			while ((fromServer = client.in.readLine()) != null) {
			    System.out.println("Server: " + fromServer);
			    if (fromServer.equals("Bye."))
			    {
			    	System.out.println("I was connected but now am leaving");
			    	client.serverSocket.close();
			    	break;
			    }		        
			}
		}
	}
}