package controller;

import gui.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.client.Model;
import core.client.Model.STATE;

public class LoginController implements Observer {

	private Model model;
	private LoginView view;

	public LoginController(Model model, LoginView view) {		
		this.model = model;
		this.view = view;
		model.registerObserver(this);

		view.setAboutListener(new AboutButtonListener());
		view.setConnectListener(new ConnectButtonListener());
		view.setQuitListener(new QuitButtonListener());
	}

	@Override
	public void update() 
	{
		if(this.model.getState() == STATE.LOGIN)
		{
			view.update();
		}
	}
	
	@Override
	public void update(Object message) {
		// TODO no default
		
	}
	
	class AboutButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
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
			model.setUsername(view.getUsername());
			model.setState(STATE.CONNECTING);
			
		}
		
	}
}
