package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.AboutView;
import core.client.Model;
import core.client.ProductInfo;
import core.client.Model.STATE;

public class AboutController extends Controller {

	private Model model;
	private AboutView view;
	
	public AboutController(Model model, AboutView view) {
		super(model);
		this.model = model;
		this.view = view;

		view.setGoBackListener(new GoBackButtonListener());
	}

	@Override
	public void update() 
	{
		if(this.model.getState() == STATE.ABOUT)
		{
			System.out.println("About Update");
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
	class GoBackButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setState(STATE.LOGIN);
		}
		
	}

}
