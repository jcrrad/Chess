package core.server;

import java.util.LinkedList;

public abstract class Pool {

	protected LinkedList<ServerClient> pool;
	public Pool(int size)
	{
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

	public abstract Pair<?, ?> getPair();

}
