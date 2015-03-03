package controller;

import gui.View;
import core.client.Model;

public abstract class Controller implements Observer {

	public Controller(Model model) {
		model.registerObserver(this);
	}

	public abstract void update();

}
