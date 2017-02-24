package com.vladprn.java;

/**
 * The third realization of HashSet by method of double hashing
 */
public class HashSet3 implements HashSet {
	private String[] map;
	private int size;
	
	public HashSet3(int maxCount) {
		this.size = Math.max(maxCount, HashCodeMethods.MAX_HASH_CODE);
		map = new String[size];
		for (int i = 0; i < size; i++) {
			map[i] = null;
		}
	}

	@Override
	public void insert(String value) {
		value = value.toLowerCase();
		int hashCode1 = hashCode1(value);
		int hashCode2 = hashCode2(value);
		while (map[hashCode1] != null) {
			hashCode1 += hashCode2;
			hashCode1 %= size;
		}
		map[hashCode1] = value; 
	}
	
	@Override
	public boolean contains(String value) {
		value = value.toLowerCase();
		int hashCode1 = hashCode1(value);
		int hashCode2 = hashCode2(value);
		while (true) {
			if (map[hashCode1] == null) {
				return false;
			}
			
			if (value.equals(map[hashCode1])) {
				return true;
			}
			
			hashCode1 += hashCode2;
			hashCode1 %= size;
		}
	}

	/**
	 * Match usually hash code
	 * @param value the element of set
	 * @return hash code
	 */
	private int hashCode1(String value) {
		return HashCodeMethods.hashCode1(value);
	}
	
	/**
	 * Match hash code that divisible into 3
	 * @param value the element of set
	 * @return hash code
	 */
	private int hashCode2(String value) {
		return HashCodeMethods.hashCode2(value);
	}
}
