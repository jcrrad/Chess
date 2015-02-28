package controller;

import gui.ChessFrame;
import gui.View;
import core.client.Model;

public abstract class Controller implements Observer {

	protected Model model;
	protected View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.model.registerObserver(this);
	}

	public abstract void update();

}
