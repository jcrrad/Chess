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

	public enum STATE {
		LOGIN, CONNECTING, PAIRED, INGAME, ABOUT, QUIT
	}
	
	STATE state = STATE.LOGIN;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	Connection connection;
	private final String hostname = "localhost";
	private final int port = 8000;
	
	public void connect()
	{
		try {
			connection = new Connection(hostname, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setState(STATE.CONNECTING);
		//connection.handShake(this);
		Thread t = new Thread(new InputHandler(connection));
		setState(STATE.INGAME);

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

	public void setState(STATE state) 
	{
		this.state = state;
		notifyObservers();
	}

	public STATE getState() 
	{
		return this.state;
	}

}