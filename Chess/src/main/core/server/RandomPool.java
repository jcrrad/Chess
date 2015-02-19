package core.server;

public class RandomPool extends Pool{
	
	public RandomPool(int size) {
		super(size);
	}

	private int size;
	
	@Override
	public Pair getPair() 
	{
		ServerClient c1,c2;
		
		System.out.println(pool.size());
		c1 = this.pool.removeFirst();
		c2 = this.pool.removeFirst();
		
		if(c1 == null || c2 == null)
		{
			System.out.println("Should dnot have got here, this is a defensive check");
		}
		
		return new Pair(c1,c2);
	}
}
