package com.vladprn.java;

/**
 * The first realization of HashSet by method of chains
 */
public class HashSet1 implements HashSet {
	private List[] map;
	
	/**
	 * The constructor
	 */
	public HashSet1() {
		map = new List[HashCodeMethods.MAX_HASH_CODE];
		for (int i = 0; i < HashCodeMethods.MAX_HASH_CODE; i++) {
			map[i] = new List();
		}
	}
	
	@Override
	public void insert(String value) {
		value = value.toLowerCase();
		int hashCode = hashCode(value);
		map[hashCode].insert(value);;
	}
	
	@Override
	public boolean contains(String value) {
		value = value.toLowerCase();
		int hashCode = hashCode(value);
		return map[hashCode].contains(value);
	}
	
	/**
	 * Match hash code
	 * @param value the element of set
	 * @return hash code
	 */
	private int hashCode(String value) {
		return HashCodeMethods.hashCode1(value);
	}
}