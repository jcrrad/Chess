package controller;
import controller.Observer;


public interface Observable {
	
   public void registerObserver(Observer observer);

   public void unregisterObserver(Observer observer);

   public void notifyObservers();

}
