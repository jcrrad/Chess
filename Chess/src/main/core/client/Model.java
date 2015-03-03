package core.client;

import gui.BoardPanel;
import gui.Square;

import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Observable;
import controller.Controller;
import controller.Observer;
import controller.piece.Piece;


public class Model implements Observable {

	public enum STATE {
		LOGIN, CONNECTING, PAIRED, INGAME, ABOUT, QUIT
	}
	
	STATE state = STATE.LOGIN;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	Connection connection;
	private final String hostname = "localhost";
	private final int port = 8000;
	BoardPanel board = new BoardPanel();
	
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
	
	public void setPiece(Piece newPiece, int x, int y)
	{
		this.board.setPiece(newPiece, x, y);
	}
	
	public Piece getPiece(int x, int y)
	{
		return this.board.getPiece(x, y);
	}
	
	public Square getSquare(int x, int y)
	{
		return this.board.getSquare(x, y);
	}
	
	public void removePiece(int x, int y)
	{
		this.board.removePiece(x, y);
	}

	public boolean movePiece(int x1, int y1, int x2, int y2)
	{
		// TODO: Fill this in
		return true;
	}
	
	public boolean walkBoard(int x1, int y1, int x2, int y2)
	{
		return this.board.walk(x1, y1, x2, y2);
	}
	
	public boolean check(Color playerColor)
	{
		return this.board.check(playerColor);
	}
	
	public boolean checkmate(Color playerColor)
	{
		return this.board.checkmate(playerColor);
	}
}