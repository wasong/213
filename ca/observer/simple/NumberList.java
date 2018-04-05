package ca.observer.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Simple observable and Iterable list of numbers.
 */
public class NumberList implements Iterable<Integer>{
	private List<Integer> list = new ArrayList<Integer>();
	
	public void insert(Integer value) {
		list.add(value);
		notifyObservers();
	}

	@Override
	public Iterator<Integer> iterator() {
		return Collections.unmodifiableList(list).iterator();
	}

	
	/**
	 * Code to handle being observable 
	 */
	// (Should put this list at top with other fields!)
	private List<NumberListObserver> observers = new ArrayList<>();
	
	public void addObserver(NumberListObserver observer) {
		observers.add(observer);
	}
	private void notifyObservers() {
		for (NumberListObserver observer : observers) {
			observer.stateChanged();
		}
	}
}
