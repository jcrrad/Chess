package core.server;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Pool {

	private ArrayList<ServerClient> waitList;
	protected LinkedList<ServerClient> pool;
	private int size;
	
	public Pool(int size)
	{
		this.size = size;
		this.waitList = new ArrayList<ServerClient>();
		this.pool = new LinkedList<ServerClient>();
	}

	public void add(ServerClient client)
	{
		this.pool.add(client);
	}
	
	public int getPoolSize()
	{
		return this.pool.size();
	}

	public abstract Pair getPair();

}
