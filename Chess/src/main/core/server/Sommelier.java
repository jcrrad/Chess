package core.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;

public class Sommelier implements Runnable {
	
	private Pool pool;
	private java.util.Date date;
	private ArrayList<ServerClient> waitList;
	private ArrayList<GameServer> games;

	public Sommelier(Pool pool)
	{
		this.pool = pool;
		date= new java.util.Date();
		waitList = new ArrayList<ServerClient>();
		games = new ArrayList<GameServer>();
	}
	
	@Override
	public void run() {
		Pair pair = null;
		GameServer game = null;
		while(true)
		{
			if (this.pool.getPoolSize() > 1)
			{
				pair = this.pool.getPair();
				game = new GameServer(pair);
				games.add(game);
				new Thread(game).start();
			}
			else
			{
				waitForWork();
			}
		}
	}
	
	private synchronized void flushWaitList() 
	{
		System.out.println("Consoladating waitList");
		System.out.println(waitList.size());
		for(ServerClient c : this.waitList)
		{
			System.out.println("Adding to pool");
			// Inspect if this client was already in a previous game.
			this.pool.add(c);
		}
		this.waitList.clear();
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
			System.out.println("waiting for work.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param client
	 * Adds clients to a waitlist, and initializes pairing if waitlist is greater than 1
	 */
	public synchronized void register(ServerClient client) 
	{
		//adds a client to the ready list
		System.out.println("Adding client to the readylist. (IP: " + client.getIPAddress() + ")");
		this.waitList.add(client);
		if( this.waitList.size() > 2)
		{
			notifyAll();
		}
	}
}