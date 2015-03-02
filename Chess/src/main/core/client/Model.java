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
		LOGIN, CONNECTING, CHATTING, INGAME, ABOUT, QUIT
	}
	
	STATE state = STATE.LOGIN;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	Connection connection;
	private final String hostname = "localhost";
	private final int port = 8000;
	private boolean boardOwner = false;
	private String chatText;
	
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


	public String getServerHostname() 
	{
		return hostname;
	}
	
	public int getServerPort()
	{
		return port;
	}

	public void setBoardOwner(boolean b) 
	{
		this.boardOwner = b;
	}

	public void setCurrentChat(String chatText) 
	{
		this.chatText = chatText;
	}
	
	public String getCurrentChat() 
	{
		return this.chatText;
	}

	public void updateBoard(Object board) 
	{
		// TODO Auto-generated method stub
		
	}

}