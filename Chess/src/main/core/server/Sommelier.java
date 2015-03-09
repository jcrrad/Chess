package core.server;

import java.util.ArrayList;

public class Sommelier implements Runnable {
	
	private Pool pool;
	private ArrayList<ServerClient> waitList;
	private ArrayList<GameServer> games;

	public Sommelier(Pool pool)
	{
		this.pool = pool;
		new java.util.Date();
		waitList = new ArrayList<ServerClient>();
		games = new ArrayList<GameServer>();
	}
	
	@Override
	public void run() { 
		Pair<?, ?> pair = null;
		GameServer game = null;
		while(true)
		{
			if (this.pool.getPoolSize() > 1)
			{
				pair = this.pool.getPair();
				game = new GameServer(pair);
				games.add(game);
				checkGamesStatus();
			}
			else
			{
				waitForWork();
			}
		}
	}
	private void checkGamesStatus() 
	{
		for(GameServer g : this.games)
		{
			if(g.getState() == STATE.GAMEOVER)
			{
				this.games.remove(g);
			}
		}
	}

	private synchronized void waitForWork()
	{
		// Putting this check here makes sure that a client can't register
		// while we are checking the size, this eliminates the possibility of a 
		// client registering after we check size but not got to this method
		if(this.waitList.size() > 1 )
		{
			flushWaitList();
		}
		else
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void flushWaitList() 
	{
		for(ServerClient c : this.waitList)
		{
			// Inspect if this client was already in a previous game.
			this.pool.add(c);
		}
		this.waitList.clear();
	}

	/**
	 * @param client
	 * Adds clients to a waitlist, and initializes pairing if waitlist is greater than 1
	 */
	public synchronized void register(ServerClient client) 
	{
		//adds a client to the ready list
		//System.out.println("Adding client to the readylist. (IP: " + client.getIPAddress() + ")");
		this.waitList.add(client);
		if( this.waitList.size() > 1)
		{
			notifyAll();
		}
	}
}