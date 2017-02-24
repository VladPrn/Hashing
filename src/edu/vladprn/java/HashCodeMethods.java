package edu.vladprn.java;

/**
 * Abstract class for store constants and functions for computing of hash code of string
 */
public abstract class HashCodeMethods {
	public static final int EN = 26;
	public static final int HASH_COUNT = 3;
	public static final int MAX_HASH_CODE = (EN*EN*EN);
	
	/**
	 * Match usually hash code
	 * @param value the element of set
	 * @return hash code
	 */
	public static int hashCode1(String value) {
		int hashCode = 0;
		int mn = 1;
		int to = HASH_COUNT;
		if (value.length() < HASH_COUNT) {
			to = value.length();
		}
		
		for (int i = 0; i < to; i++) {
			hashCode += (value.charAt(i) - 'a') * mn;
			mn *= EN;
		}
		
		return hashCode;
	}
	
	/**
	 * Match hash code that divisible into 3
	 * @param value the element of set
	 * @return hash code
	 */
	public static int hashCode2(String value) {
		int hashCode = 0;

		for (int i = 0; i < value.length(); i++) {
			hashCode += (value.charAt(i) - 'a');
		}
		
		while (hashCode % 3 != 0) {
			hashCode++;
		}
		
		hashCode %= MAX_HASH_CODE;
		
		return hashCode;
	}
}
