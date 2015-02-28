package controller;

import gui.View;
import core.client.Model;

public abstract class Controller implements Observer{
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view)
	{
		this.model = model;
		this.view = view;
		this.model.registerObserver(this);
	}
	
	public abstract void update();

}
