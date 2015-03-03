package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.LoginView;
import gui.View;
import core.client.Model;
import core.client.Model.STATE;

public class LoginController implements Observer {

	private Model model;
	private LoginView view;

	public LoginController(Model model, LoginView view) {		
		this.model = model;
		this.view = view;
		model.registerObserver(this);
		
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
		if(this.model.getState() == STATE.LOGIN)
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
			model.setState(STATE.ABOUT);
		}
		
	}
	
	class QuitButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState(STATE.QUIT);
			view.quit();
		}
		
	}
	
	class ConnectButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState(STATE.CONNECTING);
			
		}
		
	}
}
