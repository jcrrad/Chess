package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gui.GameView;
import gui.Square;

import java.awt.Color;
import java.awt.event.ActionEvent;

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
import core.client.Coordinate;
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
	@Mock private Square s;
	@Mock private ActionEvent e;
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
	
	@Test
	public void testIsBlankOrOppPiece_diffColors()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		when(s.getText()).thenReturn("K");
		when(s.getColor()).thenReturn(Color.BLACK);
		when(model.getColor()).thenReturn(Color.WHITE);
		
		assertTrue(l.isBlankOrOpponentPiece(s));
	}
	
	@Test
	public void testIsBlankOrOppPiece_blankSquare()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		when(s.getText()).thenReturn("");
		
		assertTrue(l.isBlankOrOpponentPiece(s));
	}
	
	@Test
	public void testIsBlankOrOppPiece_sameColor()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		when(s.getText()).thenReturn("K");
		when(s.getColor()).thenReturn(Color.BLACK);
		when(model.getColor()).thenReturn(Color.BLACK);
		
		assertFalse(l.isBlankOrOpponentPiece(s));
	}
	
	@Test
	public void testRecordPickup()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		Coordinate c = new Coordinate();
		c.setX(1);
		c.setY(1);
		l.recordPickUp(c);
		
		Coordinate start = l.start;
		assertEquals(start, c);
	}
	
	@Test
	public void testProcessMove_validMove()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		Board b = new Board(emptyBoard);
		Coordinate c1, c2;
		c1 = new Coordinate();
		c2 = new Coordinate();
		
		when(model.tryPlayerMove(c1, c2)).thenReturn(true);
		when(model.getBoard()).thenReturn(b);
		when(model.getConnection()).thenReturn(null);
		Mockito.doNothing().when(view).setPiece(any(String.class), any(Integer.class),
				any(Integer.class), any(Color.class));
		
		l.processMove(c1, c2);
		
		verify(view, times(64)).setPiece(any(String.class), any(Integer.class),
				any(Integer.class), any(Color.class));
		verify(view).lockBoard();
		
	}
	
	@Test
	public void testProcessMove_oppMove()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		Coordinate c1, c2;
		c1 = new Coordinate();
		c2 = new Coordinate();
		
		when(model.tryPlayerMove(c1, c2)).thenReturn(false);
		when(model.getColor()).thenReturn(Color.BLACK);
		when(model.isInCheckmate(Color.BLACK)).thenReturn(false);
		
		l.processMove(c1, c2);
		
		verify(view).lockBoard();
		verify(view).unlockBoard();
		verify(model).tryPlayerMove(c1, c2);
	}
	
	@Test
	public void testActionPerformed_blankAndNoStart()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		when(e.getSource()).thenReturn(s);
		when(s.getText()).thenReturn("");
		l.start = null;
		
		l.actionPerformed(e);
		
		verify(view, never()).update();
		verify(model, never()).getColor();
	}
	
	@Test
	public void testActionPerformed_recordPickUp()
	{
		BoardPieceListener l = controller.new BoardPieceListener();
		when(e.getSource()).thenReturn(s);
		
		when(s.getText()).thenReturn("K");
		when(s.getColumn()).thenReturn(1);
		when(s.getRow()).thenReturn(1);
		when(s.getColor()).thenReturn(Color.BLACK);
		
		when(model.getColor()).thenReturn(Color.BLACK);
		
		l.actionPerformed(e);
		
		assertEquals(1, l.start.getX());
		assertEquals(1, l.start.getY());
	}
	
	@Test
	public void testActionPerformed_processMove()
	{
		Board b = new Board(emptyBoard);
		BoardPieceListener l = controller.new BoardPieceListener();
		when(e.getSource()).thenReturn(s);
		
		when(s.getText()).thenReturn("K");
		when(s.getColumn()).thenReturn(1);
		when(s.getRow()).thenReturn(1);
		when(s.getColor()).thenReturn(Color.BLACK);
		
		Coordinate c = new Coordinate();
		c.setX(0);
		c.setY(0);
		l.start = c;
		
		when(model.tryPlayerMove(any(Coordinate.class), any(Coordinate.class))).thenReturn(true);
		when(model.getConnection()).thenReturn(null);
		when(model.getBoard()).thenReturn(b);
		when(model.getColor()).thenReturn(Color.BLACK);
		
		l.actionPerformed(e);
		
		verify(view).update();
		verify(view).lockBoard();
		
	}
	
	
	
	
}
