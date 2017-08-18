/**
 * Simple implementation of a hash table with open addressing
 *
 * @author Michael Wilson
 * @version 1.0 */

public class HashtableOA {
	/** Represents the number of elements to table size before needing to resize */
	private final double LOAD_FACTOR = 0.67;

	/** Default size of the table */
	private final int DEFAULT_CAPACITY = 10;

	/** Keeps track of the number of elements in the table */
	private int size;

	/** Keeps track of the collection of elements */
	private String[] table;

	/** Constructs the empty hashtable */
	public HashtableOA() {
		size = 0;
		table = new String[DEFAULT_CAPACITY];
	}	 

	/** 
	 * Simple insertion method for the hash table 
	 * 
	 * @param value The value to add */
	public void insert(final String value) {
		table[hash(value)] = value;
		size++;
		if(LOAD_FACTOR <= (double)(size/table.length)) {
			rehash();
		}
	}

	/**
	 * Simple deletion method for the hash table
	 *
	 * @param value The value to delete */
	public void delete(final String value) {
		table[hash(value)] = null;
		size--;
	}

	/**
	 * Seeks the index of the item that we are looking for, fortunately
	 * linear probing does that for us
	 * 
	 * @param str The value to search for 
	 * @return The index that the item was found at */
	public int search(final String str) {
		return hash(str);
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
		hashCode %= table.length;
		hashCode = ensureNoOverflow(hashCode);

		// check to make sure there is no collision, if there is then probe
		hashCode = linearProbe(hashCode, hashString);

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
			hashCode += table.length;
		}
		return hashCode;
	}

	/** 
	 * Private helper method that ensures there is a valid spot to hash to
	 * If there is not then an exception is thrown, quadratic may be better
	 *
	 * @param hashCode The hashcode to validate
	 * @param str The string to hash
	 * @throws Illegalstate exception. VERY BAD THINGS CAN HAPPEN!!! :O 
	 * @return The probing index */
	private int linearProbe(int hashCode, final String str) throws IllegalStateException {
		int probe_index = hashCode;

		// checks: space is empty and duplicate is not found
		if(table[hashCode] != null && !table[hashCode].equals(str)) {
			probe_index = hashCode + 1;			
			while(table[probe_index] != null && probe_index != hashCode) {					
				probe_index++;

				// loop around to the front of the table to keep checking
				if(probe_index >= table.length) {
					probe_index = 0;
				}
			}

			// index could not be found and our method failed
			if(probe_index == hashCode) {
				throw new IllegalStateException("BAD THINGS DO HAPPEN ):");
			}
		}
		return probe_index;
	}

	/** Doubles the size of the array while rehashing everything */
	private void rehash() {
		// save the old elements
		String[] oldTable = table;

		// double the size of the current table
		table = new String[table.length * 2];
		for(int i = 0; i < oldTable.length; i++) {
			// rehash the valid values to the new table
			if(oldTable[i] != null) {
				table[hash(oldTable[i])] = oldTable[i];
			}
		}
	}
}