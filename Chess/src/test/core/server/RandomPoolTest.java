package core.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomPoolTest 
{
	@Mock private ServerClient sc1;
	@Mock private ServerClient sc2;

	@Test
	public void testAdd_andGetSize()
	{
		RandomPool pool = new RandomPool(2);
		pool.add(sc1);
		assertEquals(1, pool.getPoolSize());
	}
	
	@Test
	public void testGetPair()
	{
		RandomPool pool = new RandomPool(2);
		pool.add(sc1);
		pool.add(sc2);
		Pair<ServerClient, ServerClient> p = new Pair<ServerClient, ServerClient>(sc1, sc2);
		assertNotNull(pool.getPair());
	}
}
