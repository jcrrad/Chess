package core.client;

public class Message {
	
	private boolean boardUpdate = false;
	private boolean hasChat = false;
	private String chatText;
	private String board;
	private boolean clientsTurn = false;
	private boolean disconnected = false;
	private boolean stalemate = false;
	private String username;

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

	public boolean hasReconnected() {
		return !disconnected;
	}

	public boolean isDisconnected() {
		return disconnected;
	}

	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}

	public boolean isStalemate() {
		return stalemate;
	}

	public void setStalemate(boolean stalemate) {
		this.stalemate = stalemate;
	}
	
	public void setBoard(String board)
	{
		this.boardUpdate = true;
		this.board = board;
	}
	
	public String getBoard()
	{
		return this.board;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return this.username;
	}
}
