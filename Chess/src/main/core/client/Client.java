package core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private String username;

	public Client()
	{
		this.username = "guest_user";
	}
	public Client(String username)
	{
		this.username = username;
	}
}
