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
}
