package core.server;

import java.io.IOException;
import java.io.OutputStream;

public class GameServer{

	private Pair pair;
	private String state;
	
	public GameServer(Pair pair) 
	{
		this.pair = pair;
		connectUsers();
		handShake();
	}


	private void handShake() 
	{
		System.out.println("Handshakes");
		((ServerClient) this.pair.client1).send("HandShake: Welcome to a game server. You decide who goes first.");
		((ServerClient) this.pair.client2).send("HandShake: Welcome to a game Server.");
	}


	private void connectUsers() 
	{
		
		ServerClient c1 = (ServerClient)pair.client1;
		ServerClient c2 = (ServerClient)pair.client2;
		System.out.println("Connecting users");
		try {
			c1.connect(c2);
			c2.connect(c1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Creating threads");
		new Thread(c1).start();
		new Thread(c2).start();
	}

	//@Override
	//public void run() 
	//{
	//	try {
	//		startGame();
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	//}
	//
	//private void startGame() throws IOException
	//{
	//	int cnt = 0;
	//	String output;
	//	ServerClient c = (ServerClient)this.pair.client1;
	//	while(true)
	//	{
	//			//while ((inputLine = in.readLine()) != null) {
	//		    //    outputLine = kkp.processInput(inputLine);
	//		    //    out.println(outputLine);
	//		    //    if (outputLine.equals("Bye."))
	//		    //        break;
	//		    //}
	//			
	//		//}
	//		//catch(Exception e)
	//		//{
	//		//	pause();
	//		//}
	//	}
	//}
	
	private synchronized void pause()
	{
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
