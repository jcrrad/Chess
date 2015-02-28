package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.AboutView;
import gui.View;
import core.client.Model;

public class AboutController extends Controller {

	public AboutController(Model model, AboutView view) {
		super(model, view);
		view.setGoBackListener(new GoBackButtonListener());
	}

	@Override
	public void update() 
	{
		if(this.model.getState() == "about")
		{
			System.out.println("About Update");
			view.update();
		}
		
	}
	class GoBackButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState("login");
		}
		
	}

}
