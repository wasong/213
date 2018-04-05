package ca.no_observer.full;

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
	public void clear() {
		list.clear();
	}
	
	/*
	 * Functions to compute statistics about this number list
	 * ------------------------------------------------------
	 */
	public int size() {
		return list.size();
	}

	public int min() {
		if (list.size() == 0) {
			throw new RuntimeException("List must have at least one element.");
		}
		int min = list.get(0);
		for (Integer value : list) {
			if (value < min) {
				min = value;
			}
		}
		return min;
	}

	public int max() {
		if (list.size() == 0) {
			throw new RuntimeException("List must have at least one element.");
		}
		int max = list.get(0);
		for (Integer value : list) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	public int sum() {
		int sum = 0;
		for (Integer value : list) {
			sum += value;
		}
		return sum;
	}

	/*
	 * Functions to support being iterable.
	 * ------------------------------------------------------
	 */
	@Override
	public Iterator<Integer> iterator() {
		return Collections.unmodifiableList(list).iterator();
	}

}
