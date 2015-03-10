package controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gui.GameView;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import controller.GameWindowController.BoardPieceListener;
import controller.GameWindowController.ButtonPanelQuitListener;
import controller.GameWindowController.ChatPanelSubmitListener;
import core.client.Connection;
import core.client.Message;
import core.client.Model;
import core.client.Model.STATE;
import core.client.game.Board;

@RunWith(MockitoJUnitRunner.class)
public class GameWindowControllerTest 
{
	private static final String 
		emptyBoard="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	@Captor private ArgumentCaptor<String> captor;
	
	@Mock private Board board;
	@Mock private Model model;
	@Mock private GameView view;
	@Mock private Message msg;
	@Mock private Connection conn;
	@Mock private JOptionPane pane;
	@InjectMocks private GameWindowController controller;
	
	
	@Test
	public void updateBoardUI()
	{
		Board b = new Board(emptyBoard);
		Mockito.doNothing().when(view).setPiece(any(String.class), any(Integer.class),
				any(Integer.class), any(Color.class));
		controller.updateBoardUI(b);
		
		verify(view, times(64)).setPiece(any(String.class), any(Integer.class),
				any(Integer.class), any(Color.class));
	}
	
	@Test
	public void testUpdate_inGame_isStartingPlayer()
	{
		Board b = new Board(emptyBoard);
		
		when(model.getState()).thenReturn(STATE.INGAME);
		when(model.getBoard()).thenReturn(b);
		when(model.isStartingPlayer()).thenReturn(true);
		Mockito.doNothing().when(view).setPiece(any(String.class), any(Integer.class),
				any(Integer.class), any(Color.class));
		
		controller.update();
		
		verify(view).update();
		verify(view).unlockBoard();
		verify(view, never()).clearChat();
		verify(model, never()).clearBoard();
	}
	
	@Test
	public void testUpdate_inGame_notStartingPlayer()
	{
		Board b = new Board(emptyBoard);
		
		when(model.getState()).thenReturn(STATE.INGAME);
		when(model.getBoard()).thenReturn(b);
		when(model.isStartingPlayer()).thenReturn(false);
		Mockito.doNothing().when(view).setPiece(any(String.class), any(Integer.class),
				any(Integer.class), any(Color.class));
		
		controller.update();
		
		verify(view).update();
		verify(view, never()).unlockBoard();
		verify(view, never()).clearChat();
		verify(model, never()).clearBoard();
	}
	
	@Test
	public void testUpdate_login()
	{
		when(model.getState()).thenReturn(STATE.LOGIN);
		
		controller.update();
		
		verify(model).clearBoard();
		verify(view).clearChat();
		verify(view, never()).unlockBoard();
		verify(view, never()).update();
	}
	
	@Test
	public void testUpdate_neitherState()
	{
		when(model.getState()).thenReturn(STATE.CONNECTING);
		
		controller.update();
		
		verify(view, never()).update();
		verify(view, never()).unlockBoard();
		verify(view, never()).clearChat();
		verify(model, never()).clearBoard();
	}
	
	@Test
	public void testUpdateMessage_updateBoard()
	{
		Message msg = new Message();
		msg.setBoard(emptyBoard);
		
		controller.update(msg);
		
		verify(model).setBoard(any(Board.class));
		verify(view).unlockBoard();
		verify(view).update();
	}
	
	@Test
	public void testUpdateMessage_updateChat_blankUserName()
	{
		String chatTxt = "This is a test";
		Message msg = new Message();
		msg.setChatText(chatTxt);
		msg.setUsername("");
		
		controller.update(msg);
		
		verify(view).updateChat(captor.capture());
		String chat = captor.getValue().toString().toLowerCase();
		assertTrue(chat.contains("opponent"));		 
		
	}
	
	@Test
	public void testUpdateMessage_updateChat_withUserName()
	{
		String chatTxt = "This is a test";
		Message msg = new Message();
		msg.setChatText(chatTxt);
		msg.setUsername("user");
		
		controller.update(msg);
		
		verify(view).updateChat(captor.capture());
		String chat = captor.getValue().toString().toLowerCase();
		assertTrue(chat.contains("user"));		 
		
	}
	
	@Test
	public void testQuitListener()
	{
		ButtonPanelQuitListener listener = controller.new ButtonPanelQuitListener();
		listener.actionPerformed(null);
		
		verify(model).setState(STATE.QUIT);
		verify(view).quit();
	}
	
	@Test
	public void testChatPanelSubmit()
	{
		String chatMsg = "this is chat";
		when(view.getChatPanelInputField()).thenReturn(chatMsg);
		when(model.getConnection()).thenReturn(conn);
		
		ChatPanelSubmitListener l = controller.new ChatPanelSubmitListener();
		l.actionPerformed(null);
		
		verify(view).updateChat(captor.capture());
		String chatSent = captor.getValue().toString().toLowerCase();
		assertTrue(chatSent.contains(chatMsg));
	}
	
	@Test
	public void testSendBoardMessage()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		when(board.toString()).thenReturn(emptyBoard);
		when(model.getConnection()).thenReturn(conn);
		when(model.getBoard()).thenReturn(board);
		Mockito.doNothing().when(conn).send(any(Message.class));
		
		l.sendBoardMessage();
		
		verify(model).getBoard();
		verify(conn).send(any(Message.class));
	}
	
}
