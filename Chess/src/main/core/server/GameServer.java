package core.server;

import java.io.OutputStream;

public class GameServer implements Runnable {

	private Pair pair;
	
	public GameServer(Pair pair) 
	{
		this.pair = pair;
		connectUsers();
		//private void pair() 
		//{
			//ServerClient c1 = this.pool.remove(0);
			//ServerClient c2 = this.pool.remove(0);
			//System.out.println("Creating game server.");
			//System.out.println(date.getTime() + " : Client 1 IP: " + c1.getIPAddress());
			//System.out.println(date.getTime() + " : Client 2 IP: " + c2.getIPAddress());
			//c1.send("Bye.");
			//c2.send("Bye.");
			//c1.disconnect();
			//c2.disconnect();
		//}
	}

	private void connectUsers() 
	{
		ServerClient c1 = (ServerClient)pair.client1;
		ServerClient c2 = (ServerClient)pair.client2;

		//c1.setOutputStream(c2.getInputStream());
	//	c2.setOutputStream(c1.getInputStream());
		
	}

	@Override
	public void run() 
	{
		startGame();
	}
	
	private void startGame()
	{
		while(true)
		{
			try
			{
				ServerClient c = (ServerClient)this.pair.client1;
				//c.socket.
				c.send("hello little one");
				//
				//while ((inputLine = in.readLine()) != null) {
			    //    outputLine = kkp.processInput(inputLine);
			    //    out.println(outputLine);
			    //    if (outputLine.equals("Bye."))
			    //        break;
			    //}
				
			}
			catch(Exception e)
			{
				pause();
			}
		}
	}
	
	private synchronized void pause()
	{
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
