package core.client;


public class Message
{
	public boolean chatMessage;
	public boolean boardMessage;
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
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
}
