package controller;

import gui.AboutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.client.Model;
import core.client.Model.STATE;
import core.client.ProductInfo;

public class AboutController implements Observer{

	private Model model;
	private AboutView view;
	
	public AboutController(Model model, AboutView view) {
		this.model = model;
		this.view = view;
		model.registerObserver(this);

		view.setGoBackListener(new GoBackButtonListener());
	}

	@Override
	public void update() 
	{
		if(this.model.getState() == STATE.ABOUT)
		{
			ProductInfo info = model.getProductInformation();
			
			view.setTitle(info.getTitle());
			view.setDescription(info.getDescription());
			view.setVersion(info.getVersion());
			view.setCopyWrite(info.getCopywrite());
			view.setAuthors(info.getAuthors());
			view.setReleaseDate(info.getReleaseDate());
			this.view.update();
		}
		
	}
	
	@Override
	public void update(Object message) {
		// TODO no default
		
	}
	
	class GoBackButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState(STATE.LOGIN);
		}	
	}
}
