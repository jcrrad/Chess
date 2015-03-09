package controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import gui.GameView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;
import core.client.game.Board;

@RunWith(MockitoJUnitRunner.class)
public class GameWindowControllerTest 
{
	private static final String 
		emptyBoard="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	@Mock private Board board;
	@Mock private Model model;
	@Mock private GameView view;
	@Mock private Message msg;
	@InjectMocks private GameWindowController controller;
	
	@Test
	public void update()
	{
		when(model.getState()).thenReturn(STATE.INGAME);
	}
}
