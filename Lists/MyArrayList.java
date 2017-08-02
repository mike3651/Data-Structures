/**
 * @author Michael Wilson
 * @version 1.0 */
public class MyArrayList{
	/** The defualt size of the arraylist */
	private static final int DEFAULT_SIZE = 10;

	/** The size of the contents in the array list */
	private int contentSize;

	/** The array that holds our items */
	private int[] myArray;

	/** Default constructor */
	public MyArrayList() {
		this(DEFAULT_SIZE);
	}

	/** 
	 * Overloaded constructor 
	 *
	 * @param theSize The size of the array that the user wants */
	public MyArrayList(final int theSize) {
		contentSize = 0;
		myArray = new int[theSize];
	}


	/** 
	 * Adds an item to the end of the list
	 * 
	 * @param item The item to add */
	public void add(final int item) {
		add(item, contentSize);
	}

	/**
	 * Adds an item at the given index, may require shifting
	 *
	 * @param item The item to add 
	 * @param index The location to insert the item */
	public void add(final int item, final int index) {		
		if(index > contentSize) {
			System.out.println("Can't add the item at an invalid index");
			return;
		}
		contentSize++;		
		if(contentSize >= myArray.length) {
			doubleSize();
		}
		for(int i = contentSize - 1; i > index; i--) {			
			// shift the elements over to the right			
			myArray[i] = myArray[i - 1];			
		}
		myArray[index] = item;
	}

	/**
	 * Returns the item at the given index 
	 *
	 * @return The item at the given index, -1 if not found */
	public int getItem(final int index) {		
		return index < contentSize ? myArray[index] : -1;
	}


	/** 
	 * Removes the first instance of the item in the list 
	 * 
	 * @return True if the item was successfully removed, false otherwise */
	public boolean remove(final int item) {
		int removalLocation = -1;
		for(int i = 0; i < contentSize; i++) {
			if(myArray[i] == item) {
				removalLocation = i;
				break;
			}
		}
		
		// check to see if the location is valid
		if(removalLocation == -1) {
			return false;
		}

		// shift the items over to the left
		for(int i = removalLocation; i < contentSize; i++) {
			myArray[i] = myArray[i + 1];
		} 		
		contentSize--;		
		return true;

	}

	/** 
	 * Returns the size of the list 
	 * 
	 * @return The size of the list */
	public int getSize() {
		return contentSize;
	}


	/** 
	 * Simple string representation fo the list 
	 * 
	 * @return String version of the list */
	public String toString() {
		StringBuilder myString = new StringBuilder();		
		myString.append("[");
		if(contentSize != 0) {
			myString.append(myArray[0]);
			for(int i = 1; i < contentSize; i++) {
				myString.append(", ");
				myString.append(myArray[i]);
			}
		}
		myString.append("]");
		return myString.toString();
	}

	/** Trims the size of the array list */
	private void trimToSize() {
		if(contentSize < myArray.length) {
			changeSize(contentSize);			
		}
	}

	/** Resizes the array by 2-fold */
	private void doubleSize() {
		changeSize(myArray.length * 2);
	}

	/** 
	 * Helper method to iterate through the contents of our array 
	 * 
	 * @param theSize The size of the new array */
	private void changeSize(final int theSize) {
		// begin copying over data if necessary
		int[] newArray = new int[theSize];
		for(int i = 0; i < contentSize; i++) {
			newArray[i] = myArray[i];
		}
		myArray = newArray;
	}


}