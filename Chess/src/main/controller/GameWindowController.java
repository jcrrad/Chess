package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GameView;
import core.client.Model;
import core.client.Model.STATE;

public class GameWindowController extends Controller{

	public GameWindowController(Model model, GameView view) {
		super(model,view);
		view.setButtonPanelQuitListener(new ButtonPanelQuitListener());
		view.setChatPanelSubmitListener(new ChatPanelSubmitListener());
		view.setButtonPanelStalemateListener(new ButtonPanelStalemateListener());
		view.setBoardPieceListener(new BoardPieceListener());
	}

	public void sendMessage(String text) {  
		/*
		 * TODO: This will be the method to send messages out to the server,
		 * this controller will also receive messages, we should keep the null
		 * check as this will keep us from sending blank messages back and forth
		 * and wasting resources
		 */
		if (text.length() != 0)
			System.out.println(text);
		// model.sendMessage();
	}

	public void handleQuit() {
		/*
		 * TODO: This will be the method called when a player quits, the player
		 * who does will be returned to the login screen, but the opposing
		 * player needs to be alerted.
		 */
		System.out.println("Quit Requested");
	}

	public void offerStalemate() {
		// TODO: This will be called when one person offers a stalemate
		System.out.println("Stalemate Offered");
	}

	public void killWindow() {
		//view.dispose();
	}

	@Override
	public void update() 
	{
		System.out.println("Checking ingame");
		if(model.getState() == STATE.INGAME)
		{
			System.out.println("INGAME");
			((GameView) view).updateChat(model.getCurrentChat());
			view.update();
		}
	}

	class ButtonPanelQuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			model.setState(STATE.QUIT);
		}	
	}
	
	class ChatPanelSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Sending Text");
		}
	}
	
	class ButtonPanelStalemateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Offering Stalemate");
		}
	}
	
	class BoardPieceListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			System.out.println("A board piece moved");
		}
	}
}
