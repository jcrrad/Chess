package core.server;

public class Pair<T,U> {

	public final T client1;
	public final U client2;
	
	public Pair(T client1, U client2)
	{
		this.client1 = client1;
		this.client2 = client2;
	}
}
