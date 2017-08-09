/** 
 * @author Michael Wilson
 * @version 1.0 */

import java.util.*;

public class SearchAndSort {
	/** Scanner to be used */
	private static Scanner myScan;

	/** Keeps track of the user's string response */
	private static String string_response;

	/** Keeps track of the user's integer responses */
	private static int integer_response;

	/** My own personal stopper */
	private static boolean valid;

	/** Array to use throughout the program */
	private static int[] myArray;

	/** Keeps track of an integer return value */
	private static int returnValue;

	static {
		myScan = new Scanner(System.in);
		valid = false;
	}

	public static void main(final String[] args) throws Exception{
		Thread.sleep(50);		
		do {
			try {
				System.out.println("How many elements would you like to put into an array?");
				integer_response = myScan.nextInt();
				valid = true;				
				System.out.println("Looks like a great number to me! :)");
			} catch(InputMismatchException e) {
				System.out.println("Sorry but it looks like that isn't a vaild number, " + 
					"please try again. ):");
			}
		} while(!valid);
		System.out.println(valid);

		valid = false;
		generateCollection();
		System.out.println("Here is what the array looks like: " 
			+ toStringArray(myArray));
		System.out.println("Generating a collection of size " + integer_response 
			+ "\nWould you like to search or sort?");

		// Since we are only dealing with search for now we will assume that the user 
		// would like to search through the array 		
		System.out.println("Would you like to run linear search or binary search?");
		string_response = myScan.next();
		System.out.println("What number would you like to find?");
		integer_response = myScan.nextInt();
		if(string_response.equals("binary")) {
			// sort the collection
			selectionSort(myArray);			
			System.out.println("The array has been sorted and looks like "
				+ toStringArray(myArray));
		} 

		returnValue = string_response.equals("binary") 
		? binarySearch(myArray, integer_response) : linearSearch(myArray, integer_response);
		System.out.println("The item was found at index: " + returnValue);

	}

	/** Function that generates an array of random numbers */
	private static void generateCollection() {
		myArray = new int[integer_response];

		// random seed 
		Random random = new Random();
		for(int i = 0; i < myArray.length; i++) {
			myArray[i] = random.nextInt(10) + 1;
		}
	}

	/** 
	 * Linear search 
	 * 
	 * @param arr The array to search through 
	 * @param item The value to search for
	 * @return The index where the item was found, -1 if not found */
	private static int linearSearch(final int[] arr, final int item) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == item) {
				return i;
			}
		}
		return -1;
	}

	/** 
	 * Binary search 
	 * 
	 * @param arr The array to search through 
	 * @param item The value to search for
	 * @return The index where the item was found, -1 if not found */
	private static int binarySearch(final int[] arr, final int item) {
		return binarySearchHelper(arr, item, 0, arr.length - 1);		
	}

	/** 
	 * Binary search 
	 * 
	 * @param arr The array to search through 
	 * @param item The value to search for
	 * @param start The start of the segment to look through
	 * @param end The end of the segment that we are looking through 
	 * @return The index where the item was found, -1 if not found */
	private static int binarySearchHelper(final int[] arr, final int item,
										  final int start, final int end) {
		if(start <= end) {
			int mid = (start + end)/2;
			if(arr[mid] == item) {
				return mid;
			} else if(arr[mid] > item) {
				return binarySearchHelper(arr, item, start, mid - 1);				
			} else {
				return binarySearchHelper(arr, item, mid + 1, end);
			}
		} else {
			return -1;
		}
	}

	/**
	 * Selection sort 
	 * 	
	 * @param arr The unsorted array to sort
	 * @pre: Unsorted array 
	 * @post: Sorted array */
	private static void selectionSort(int[] arr) {		
		if(arr.length > 1) {			
			for(int i = 0; i < arr.length; i++) {
				int min_index = i;
				for(int j = i + 1; j < arr.length; j++) {
					if(arr[min_index] > arr[j]) {
						min_index = j;
					}
				}
				swap(arr, i, min_index);
			}
		}
	}	

	/** 
	 * Swap method - To be used in other algorithms
	 * 
	 * @param arr The array to manipulate
	 * @param i The first swapping index
	 * @param j The second swapping index */
	private static void swap(int[] arr, final int i, final int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/** 
	 * Method that prints the contents of an array out in a fashion of my liking 
	 * 
	 * @param arr The array to print the contents of
	 * @return A string representation of the array */
	private static String toStringArray(final int[] arr) {
		StringBuilder myBuild = new StringBuilder();
		myBuild.append("[");
		if(arr.length > 0){
			myBuild.append(arr[0]);
			for(int i = 1; i < arr.length; i++) {
				myBuild.append(", ");
				myBuild.append(arr[i]);
			}
		}
		myBuild.append("]");
		return myBuild.toString();
	}

}