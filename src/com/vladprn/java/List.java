package com.vladprn.java;

/**
 * List of elements for using in HashSet1
 */
public class List {
	private ListElement begin;
	
	/**
	 * Constructor
	 */
	public List() {
		begin = null;
	}
	
	/**
	 * Insert element to list
	 * @param value the element
	 */
	public void insert(String value) {
		ListElement added =  new ListElement(value);
		
		added.setNext(begin);
		begin = added;
	}
	
	/**
	 * Check for the presence element in list
	 * @param value the element of list
	 * @return true unless contain <br> false unless not contain 
	 */
	public boolean contains(String value) {
		ListElement find = new ListElement(value.toLowerCase());
		
		ListElement curr = begin;
		while (curr != null) {
			if (curr.equals(find)) {
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}
}