package edu.vladprn.java;

/**
 * Standard interface of set
 */
public interface HashSet {
	/**
	 * Check for the presence element in set
	 * @param value the element of set
	 * @return true unless contain <br> false unless not contain 
	 */
	public boolean contains(String value);
	/**
	 * Add element to set
	* @param value the element of set	
	*/	
	public void insert(String value);
}