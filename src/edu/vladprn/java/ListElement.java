package edu.vladprn.java;

/**
 * The container for string, element of List 
 */
public class ListElement {
	private String value;
	private ListElement next;
	
	/**
	 * Constructor
	 * @param value the string of element
	 */
	public ListElement(String value) {
		this.value = value;
		this.next = null;
	}
	
	@Override
	public boolean equals(Object el) {
		if (el.getClass() != ListElement.class) {
			return false;
		}
		return this.value().equals(((ListElement) el).value());
	}
	
	/**
	 * Set next element for current container
	 * @param el the next element
	 */
	public void setNext(ListElement el) {
		this.next = el;
	}
	
	/**
	 * Get next element for current container
	 * @param el the next element
	 */
	public ListElement getNext() {
		return this.next;
	}
	
	/**
	 * Get string that store container
	 * @return the string
	 */
	public String value() {
		return this.value;
	}
}