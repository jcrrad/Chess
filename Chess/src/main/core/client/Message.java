package core.client;

public class Message {
	
	private boolean boardUpdate = false;
	private boolean hasChat = false;
	private String chatText;
	private boolean clientsTurn = false;
	private boolean disconnected = false;

	public boolean isClientsTurn() 
	{
		return this.clientsTurn;
	}
	
	public void setClientsTurn(boolean isClientsTurn) 
	{
		this.clientsTurn = isClientsTurn;
	}
	
	public boolean hasChat() 
	{
		return hasChat;
	}

	public boolean hasBoardUpdate() 
	{
		return boardUpdate;
	}
	
	public void setChatText(String msg)
	{
		this.hasChat = true;
		this.chatText = msg;
	}
	
	public String getChatText()
	{
		return this.chatText;
	}

	public Object getBoard() 
	{
		return null;
	}

	public boolean hasReconnected() {
		return false;
	}

	public boolean hasDisconnected() {
		return disconnected;
	}

	public boolean isDisconnected() {
		return disconnected;
	}

	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}



}
