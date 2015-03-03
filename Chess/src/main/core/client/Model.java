package core.client;

import java.io.IOException;
import java.util.ArrayList;

import controller.Observable;
import controller.Observer;


public class Model implements Observable {

	public enum STATE {
		LOGIN, CONNECTING, INGAME, ABOUT, QUIT
	}
	
	STATE state = STATE.LOGIN;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	Connection connection;
	private final String hostname = "localhost";
	private final int port = 8000;
	private ProductInfo pinfo = new ProductInfo("filename");
	private String username;
	private Board board = new Board();
	
	public Connection getConnection()
	{
		return connection;
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

	public boolean tryPlayerMove(Coordinate start, Coordinate end)
	{
		return board.movePiece(start, end);
	}
	
	public boolean isInCheckmate()
	{
		return board.isInCheckmate();
	}
	
	public ProductInfo getProductInformation() {
		return pinfo;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return this.username;
	}
}