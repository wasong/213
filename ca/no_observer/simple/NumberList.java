package ca.no_observer.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Simple Iterable list of numbers.
 */
public class NumberList implements Iterable<Integer>{
	private List<Integer> list = new ArrayList<Integer>();
	
	public void insert(Integer value) {
		list.add(value);
	}

	@Override
	public Iterator<Integer> iterator() {
		return Collections.unmodifiableList(list).iterator();
	}
}
