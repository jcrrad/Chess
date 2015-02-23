package core.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;

public class Sommelier implements Runnable {

	private ReentrantLock lock;
	private ArrayList<ServerClient> readyList;
	private LinkedList<ServerClient> pool;
	private java.util.Date date;

	public Sommelier()
	{
		this.readyList = new ArrayList<ServerClient>();
		this.lock = new ReentrantLock();
		this.pool = new LinkedList<ServerClient>();

		date= new java.util.Date();
	}
	
	@Override
	public void run() {
		//Pairing needs to be done here.
		while(true)
		{
			//System.out.println(readyList.size());
			if (this.readyList.size() > 1 )
			{
				System.out.println("Start analysis of readylist");
				this.lock.lock();
				try
				{
					for (int i = this.readyList.size(); i > 0; --i)
					{
						System.out.println("Removing from readylist");
						pool.add(this.readyList.remove(0));
					}
				}
				finally
				{
					this.lock.unlock();
				}
			}
			//Take two off the queue and pair them.
			if ( this.pool.size() > 1 )
			{
				System.out.println("Start pairing");
				pair();
			}
		}
	}
	
	private void pair() 
	{
		ServerClient c1 = this.pool.remove(0);
		ServerClient c2 = this.pool.remove(0);
		System.out.println("Creating game server.");
		System.out.println(date.getTime() + " : Client 1 IP: " + c1.getIPAddress());
		System.out.println(date.getTime() + " : Client 2 IP: " + c2.getIPAddress());
		c1.send("Bye.");
		c2.send("Bye.");
		c1.disconnect();
		c2.disconnect();
	}
	/**
	 * @param client
	 * Adds clients that recently connected to a list for pairing.
	 */
	public void register(ServerClient client) 
	{
		lock.lock();
		try
		{
			System.out.println("Adding client to the readylist. (IP: " + client.getIPAddress() + ")");
			this.readyList.add(client);
			System.out.println(readyList.size());
		} 
		finally
		{
			lock.unlock();
		}
	}
	
	

}
