package controller;


public interface Observer {
	
	public void update();
	
	public void update(Object message);

}
