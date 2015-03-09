package controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gui.LoadingView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import controller.ConnectionController.InputHandler;
import core.client.Connection;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionControllerTest 
{
	@Mock private Model model;
	@Mock private LoadingView view;
	@Mock private InputHandler handler;
	@InjectMocks private ConnectionController controller;
	
	@Test
	public void testUpdate()
	{
		when(model.getState()).thenReturn(STATE.CONNECTING);
		
		controller.update();
		
		verify(model).setConnection(any(Connection.class));
		verify(view).update();
	}
	
	@Test
	public void testNotUpdate()
	{
		when(model.getState()).thenReturn(STATE.ABOUT);
		
		controller.update();
		
		verify(view, never()).update();
		verify(model, never()).setConnection(any(Connection.class));
	}
	
	@Test
	public void testProcessInput_isClientsTurn()
	{
		Message mes = new Message();
		mes.setClientsTurn(true);
		
		when(model.getState()).thenReturn(STATE.CONNECTING);
		
		controller.processInput(mes);
		
		verify(model).setStartingPlayer(true);
		verify(model).setState(STATE.INGAME);
		verify(model).setMessage(mes);
		
	}
	
	@Test
	public void testProcessInput_notClientsTurn()
	{
		Message mes = new Message();
		mes.setClientsTurn(false);
		
		when(model.getState()).thenReturn(STATE.CONNECTING);
		
		controller.processInput(mes);
		
		verify(model, never()).setStartingPlayer(true);
		verify(model).setState(STATE.INGAME);
		verify(model).setMessage(mes);
		
	}
	
	@Test
	public void testProcessInput_isClientsTurn_notIngame()
	{
		Message mes = new Message();
		mes.setClientsTurn(true);
		
		when(model.getState()).thenReturn(STATE.ABOUT);
		
		controller.processInput(mes);
		
		verify(model, never()).setStartingPlayer(true);
		verify(model).setState(STATE.INGAME);
		verify(model).setMessage(mes);
		
	}
	
	@Test
	public void testProcessInput_notClientsTurn_notIngame()
	{
		Message mes = new Message();
		mes.setClientsTurn(false);
		
		when(model.getState()).thenReturn(STATE.ABOUT);
		
		controller.processInput(mes);
		
		verify(model, never()).setStartingPlayer(true);
		verify(model).setState(STATE.INGAME);
		verify(model).setMessage(mes);
		
	}
}
