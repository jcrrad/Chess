package core.client;

import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import controller.Observable;
import controller.Observer;
import core.client.game.Board;
import core.client.game.Piece;


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
	
	public void lockBoard()
	{
		//TODO: Lock the board
		System.out.println("Board Locked");
	}
	
	public void unlockBoard()
	{
		//TODO: Unlock the board
		System.out.println("Board Unlocked");
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
	
	public Board getBoard()
	{
		return this.board;
	}
	
	public void setBoard(Board board)
	{
		if( board != null)
			this.board = board;
	}
	
	public Piece getPiece(Coordinate location)
	{
		return this.board.getPiece(location);
	}
	
	public void setPiece(Piece piece, Coordinate location)
	{
		this.board.setPiece(piece, location);
	}
	
	public void removePiece(Coordinate location)
	{
		this.board.removePiece(location);
	}

	public boolean isInCheck(Color playerColor)
	{
		return this.board.isInCheck(playerColor);
	}
	
	public boolean isInCheckmate(Color playerColor)
	{
		return this.board.isInCheckmate(playerColor);
	}

	public boolean tryPlayerMove(Coordinate start, Coordinate end)
	{
		return board.movePiece(start, end);
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
