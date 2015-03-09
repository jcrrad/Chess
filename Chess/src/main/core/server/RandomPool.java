package core.server;

public class RandomPool extends Pool{
	
	public RandomPool(int size) {
		super(size);
	}

	@Override
	public Pair<ServerClient, ServerClient> getPair() 
	{
		ServerClient c1,c2;
		
		c1 = this.pool.removeFirst();
		c2 = this.pool.removeFirst();
		
		if(c1 == null || c2 == null)
		{
			System.out.println("Should not have got here, this is a defensive check");
		}
		
		return new Pair<ServerClient, ServerClient>(c1,c2);
	}
}
