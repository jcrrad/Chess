package core.client;

import gui.BoardPanel;

public class Message
{
	public boolean chatMessage;
	public boolean boardMessage;
	public BoardPanel board;
	public String text;
	
	public boolean isChatMessage() 
	{
		return chatMessage;
	}
	
	public void setChatMessage(boolean chatMessage) 
	{
		this.chatMessage = chatMessage;
	}
	
	public boolean isBoardMessage() 
	{
		return boardMessage;
	}
	
	public void setBoardMessage(boolean boardMessage) 
	{
		this.boardMessage = boardMessage;
	}
	
	public BoardPanel getBoard() 
	{
		return board;
	}
	
	public void setBoard(BoardPanel board) 
	{
		this.board = board;
	}
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
}
