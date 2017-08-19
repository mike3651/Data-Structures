/**
 * This class represents the separate chaining 
 * implementation of the hash table
 * 
 * @author Michael Wilson
 * @version 1.1 */

import java.util.*;

public class HashtableSC {	
	/** Default table size */
	private final int DEFAULT_CAPACITY = 10;

	/** Tells us when to resize our hashtable */
	private final double LOAD_FACTOR = 2.00;

	/** Keeps track of the number of elements in the hash table */
	private int size;

	/** The data structures that represent this hash table implementation */
	private ArrayList<LinkedList<String>> table;

	/** Constructs an empty Hash table */
	public HashtableSC() {
		size = 0;
		table = new ArrayList<LinkedList<String>>(DEFAULT_CAPACITY);
	}


	/** 
	 * Simple insertion method for the hash table 
	 * 
	 * @param value The value to add */
	public void insert(final String value) {
		table.get(hash(value)).add(value);
		size++;
		if(LOAD_FACTOR <= (double)(size/table.size())) {
			rehash();
		}
	}

	/**
	 * Simple deletion method for the hash table
	 *
	 * @param value The value to delete */
	public void delete(final String value) {
		// get the list, then remove the appropriate value
		table.get(hash(value)).remove(table.get(hash(value)).indexOf(value));
		size--;
	}

	/**
	 * Seeks the index of the item that we are looking for
	 * 
	 * @param str The value to search for 
	 * @return The index that the item was found at */
	public String search(final String str) {
		return "Found at bucket " + table.get(hash(str)) + ": at index " 
		+ table.get(hash(str)).indexOf(str) + "of the linkedlist";
	}

	/** 
	 * Simple hashing method using the idea proposed by Mark Allen Weiss
	 *
	 * @param hashString The string to be used for the hash code
	 * @return The hash code of this string */
	public int hash(final String hashString) throws IllegalStateException {
		int hashCode = 0;
		for(int i = 0; i < hashString.length(); i++) {
			hashCode *= 37 + hashString.charAt(i);
		}

		// need to mod by the size of our table to put into proper spot
		hashCode %= table.size();
		hashCode = ensureNoOverflow(hashCode);		
		return hashCode;
	}

	/**
	 * Private helper method that checks to see if the number is too larger
	 * If it is then we just simply add the table size to it to make it non negative 
	 *
	 * @param hashCode The value to make sure isn't negative 
	 * @return The hashCode that has been modified or left alone */
	private int ensureNoOverflow(int hashCode) {
		if(hashCode < 0) {
			hashCode += table.size();
		}
		return hashCode;
	}

	/** Doubles the size of the array while rehashing everything */
	private void rehash() {
		// save the old elements
		ArrayList<LinkedList<String>> oldTable = table;

		// double the size of the current table
		table = new ArrayList<LinkedList<String>>(oldTable.size() * 2);
		for(int i = 0; i < oldTable.size(); i++) {
			// rehash the valid values to the new table
			if(oldTable.get(i) != null) {								
				for(String str : oldTable.get(i)) {
					// rehash the individual nodes
					table.get(hash(str)).add(str);
				}				
			}
		}
	}
}