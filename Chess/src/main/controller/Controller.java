package controller;

import gui.ChessFrame;
import core.client.Model;

public abstract class Controller implements Observer {

	protected Model model;
	protected ChessFrame view;

	public Controller(Model model, ChessFrame view) {
		this.model = model;
		this.view = view;
		this.model.registerObserver(this);
	}

	public abstract void update();

}
