package controller;

import gui.ChessFrame;

import java.util.Observable;
import java.util.Observer;

import gui.View;

import core.client.Model;

public class LoginController extends Controller {

	public LoginController(Model model, View view) {
		super(model, view);
		System.out.println("about to update");
		view.update();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
