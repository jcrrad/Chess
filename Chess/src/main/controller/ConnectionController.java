package controller;

import gui.ChessFrame;
import core.client.Model;

public class ConnectionController {
	private ChessFrame view;
	private Model model;

	public ConnectionController(Model model) {
		this.model = model;
	}

	public void connect() {
		// TODO: This is where we will make a call to the model to connect to
		// the server
		// model.createConnection(); etc.
		System.out.println("ControllerConnect");
		view.goToGame();
	}

	public void killWindow() {
		view.dispose();
	}

	// //////////////////////////////////
	//
	// Getters & Setters
	//
	// //////////////////////////////////

	public ChessFrame getView() {
		return view;
	}

	public void setView(ChessFrame view) {
		this.view = view;
	}
}
