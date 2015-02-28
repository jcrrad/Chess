package core.client;


public class Message
{
	private boolean chatMessage;
	private boolean boardMessage;
	public long handshake = 0;
	private String text;
	private int[] start = new int[2];
	private int[] end = new int[2];
	
	public boolean isChatMessage() 
	{
		return chatMessage;
	}
	
	public boolean isBoardMessage() 
	{
		return boardMessage;
	}

	
	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.chatMessage = true;
		this.text = text;
	}
	
	public void setCoords(int startx, int starty, int endx, int endy)
	{
		this.boardMessage = true;
		
		this.start[0] = startx;
		this.start[1] = starty;
		this.end[0] = endx;
		this.end[1] = endy;
	}
	
	public int[] getStartCords()
	{
		return start;
	}
	
	public int[] getEndCords()
	{
		return end;
	}
}
