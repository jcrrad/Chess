package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.LoginView;
import gui.View;
import core.client.Model;

public class LoginController extends Controller {

	public LoginController(Model model, LoginView view) {
		super(model, view);
		System.out.println("about to update");
		view.setAboutListener(new AboutButtonListener());
		view.setConnectListener(new ConnectButtonListener());
		view.setQuitListener(new QuitButtonListener());
		view.update();
	}

	@Override
	public void update() 
	{
		System.out.println("Here in login");
		if(this.model.getState() == "login")
		{
			System.out.println("Login Update");
			view.update();
		}
	}
	
	class AboutButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Going to about button");
			model.setState("about");
		}
		
	}
	
	class QuitButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState("quit");
		}
		
	}
	
	class ConnectButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState("ingame");
			
		}
		
	}
}
