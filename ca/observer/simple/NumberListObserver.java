package ca.observer.simple;

/**
 * Interface for observers to implement to be able to observe 
 * changes to NumberList objects.
 */
public interface NumberListObserver {
	void stateChanged();
}
