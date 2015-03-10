package controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gui.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.LoginController.AboutButtonListener;
import controller.LoginController.ConnectButtonListener;
import controller.LoginController.QuitButtonListener;
import core.client.Model;
import core.client.Model.STATE;

public class LoginControllerTest 
{
	@Mock private Model model;
	@Mock private LoginView view;
	@InjectMocks private LoginController controller;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUpdate()
	{
		when(model.getState()).thenReturn(STATE.LOGIN);
		
		controller.update();
		
		verify(view).update();
	}
	
	@Test
	public void testNotUpdate()
	{
		when(model.getState()).thenReturn(STATE.ABOUT);
		
		controller.update();
		
		verify(view, never()).update();
	}
	
	@Test
	public void testAboutButton()
	{
		AboutButtonListener l = controller.new AboutButtonListener();
		l.actionPerformed(null);
		verify(model).setState(STATE.ABOUT);
	}
	
	@Test
	public void testQuitButton()
	{
		QuitButtonListener l = controller.new QuitButtonListener();
		l.actionPerformed(null);
		verify(model).setState(STATE.QUIT);
		verify(view).quit();
	}
	
	@Test
	public void testConnectButton()
	{
		ConnectButtonListener l = controller.new ConnectButtonListener();
		when(view.getUsername()).thenReturn("User");
		l.actionPerformed(null);
		verify(model).setUsername("User");
		verify(model).setState(STATE.CONNECTING);
	}
}
