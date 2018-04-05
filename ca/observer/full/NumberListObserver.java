package ca.observer.full;

/**
 * Interface for observers to implement to be able to observe 
 * changes to NumberList objects.
 */
public interface NumberListObserver {
	void stateChanged();
}
