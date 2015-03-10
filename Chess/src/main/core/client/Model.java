package core.client;

import java.awt.Color;
import java.util.ArrayList;

import controller.Observable;
import controller.Observer;
import core.client.game.Board;

public class Model implements Observable {

	public enum STATE {
		LOGIN, CONNECTING, INGAME, ABOUT, QUIT
	}

	STATE state = STATE.LOGIN;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	Connection connection;
	private ProductInfo pinfo = new ProductInfo("filename");
	private String username;
	private Board board = new Board();
	private Color color = Color.WHITE;
	private String hostname;
	private int port;

	public Model(String hostname, int port) {
		this.port = port;
		this.hostname = hostname;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer obs : observers) {
			obs.update();
		}
	}

	public void setMessage(Message msg) {
		for (Observer obs : observers) {
			obs.update(msg);
		}
	}

	public void setState(STATE state) {
		this.state = state;
		notifyObservers();
	}

	public STATE getState() {
		return this.state;
	}

	public ProductInfo getProductInformation() {
		return pinfo;
	}

	// CLIENT AND SERVER INFO
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public String getServerHostname() {
		return this.hostname;
	}

	public int getServerPort() {
		return this.port;
	}

	public void setConnection(Connection serverConnection) {
		this.connection = serverConnection;
	}

	// BOARD ACCESSORS

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		if (board != null)
			this.board = board;
	}

	public boolean isInCheckmate(Color playerColor) {
		return this.board.isInCheckmate(playerColor);
	}

	public boolean tryPlayerMove(Coordinate start, Coordinate end) {
		return board.movePiece(start, end);
	}

	public boolean isStartingPlayer() {
		return Color.BLACK.equals(this.color);
	}

	public void setStartingPlayer(boolean b) {
		if (b)
			this.color = Color.BLACK;
	}

	public Color getColor() {
		return this.color;
	}

	public void clearBoard() {
		this.board = new Board();
	}

}
