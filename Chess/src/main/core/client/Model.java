package core.client;

import java.io.Console;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Observable;
import controller.Controller;
import controller.Observer;

public class Model implements Observable {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	String state = "login";
	
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
		
		Scanner in = new Scanner(System.in);
		
		if (client != null)
		{
			String fromServer;
			
			fromServer = client.in.readLine();
			System.out.println(fromServer);
			if( fromServer.equals("HandShake: Welcome to a game server. You decide who goes first."))
			{
				fromServer = in.nextLine();
				client.out.println(fromServer);
				client.out.flush();
			}
			
			while( true )
			{
				fromServer = client.in.readLine();
				System.out.println(fromServer);
				fromServer = in.nextLine();
				client.out.println(fromServer);
				client.out.flush();
			}
		}
	}

	@Override
	public void registerObserver(Observer observer) 
	{
		this.observers.add(observer);	
	}

	@Override
	public void unregisterObserver(Observer observer) 
	{
		this.observers.remove(observer);	
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : observers)
		{
			obs.update();
		}
	}

	public void setState(String state) 
	{
		this.state = state;
		notifyObservers();
	}

	public String getState() 
	{
		return this.state;
	}

}