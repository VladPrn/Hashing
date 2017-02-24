package com.vladprn.java;

/**
 * The second realization of HashSet by method of open addressing
 */
public class HashSet2 implements HashSet {
	private String[] map;
	private int size;
	
	/**
	 * The constructor
	 * @param maxCount the max count of elements in set
	 */
	public HashSet2(int maxCount) {
		this.size = Math.max(maxCount, HashCodeMethods.MAX_HASH_CODE);
		map = new String[size];
		for (int i = 0; i < size; i++) {
			map[i] = null;
		}
	}
	
	@Override
	public void insert(String value) {
		value = value.toLowerCase();
		int hashCode = hashCode(value);
		while (map[hashCode] != null) {
			hashCode++;
			if (hashCode == size) {
				hashCode = 0;
			}
		}
		map[hashCode] = value; 
	}
	
	@Override
	public boolean contains(String value) {
		value = value.toLowerCase();
		int hashCode = hashCode(value);
		while (true) {
			if (map[hashCode] == null) {
				return false;
			}
			
			if (value.equals(map[hashCode])) {
				return true;
			}
			
			hashCode++;
			if (hashCode == size) {
				hashCode = 0;
			}
		}
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
