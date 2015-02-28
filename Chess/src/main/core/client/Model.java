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