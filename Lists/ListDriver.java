/**
 * @author Michael Wilson
 * @version 1.0 */

import java.util.*;

public class ListDriver {
	/** Scanner to be used throughout the duration of the program */
	private static Scanner myScanner;

	/** Keeps track of the response from the user */
	private static String response;


	/** Keeps track of any numbers the user may enter */
	private static int currentNumber;

	/** Keeps track of the type of list that we are dealing with */
	private static String listType;

	/** Keeps track of the Arraylist */
	private static MyArrayList myArrayList;

	/** Keeps track of the Linkedlist */

	// run only once when the class is loaded 
	static {
		myScanner = new Scanner(System.in);
		response = "";
	}

	/** 
	 * Kicks off the heart of our program 
	 * 
	 * @param args The arguments passed in from the command line */	 
	public static void main(final String[] args) {
		prompt();
	}

	/** Special prompt to be used */
	private static void prompt() {
		do {			
			System.out.println("Would you like to work with Array Lists of Linked Lists?");
			response = myScanner.next();
		}while(!response.equalsIgnoreCase("Arraylist") && 
			!response.equalsIgnoreCase("Linkedlist"));		

		if(response.equalsIgnoreCase("Arraylist")){
			runArrayList();
		} else {
			runLinkedList();
		}
	}

	/** Runs the Arraylist functionality */	
	private static void runArrayList() {		
		listType = "Array";
		System.out.println("What would you like the size of the array list to be?");
		response = myScanner.next();
		try {
			currentNumber = Integer.parseInt(response);
			myArrayList = new MyArrayList(currentNumber);

		} catch(NumberFormatException e) {
			System.out.println("Looks like I'll set the default size then");
			myArrayList = new MyArrayList();
		}

		runListPrompt();
	}

	/** Runs the Linkedlist functionality */
	private static void runLinkedList() {

	}

	/** Runs the general prompt for the list */
	private static void runListPrompt() {
		while(!response.equalsIgnoreCase("quit")) {
			System.out.println("Would you like to add an item, "
				+ "get an item, remove an item, or get the size of the list?");
			response = myScanner.next();
			
			if(response.equalsIgnoreCase("add")){				
				// ask the user for details on the addition of the new element
				System.out.println("What number would you like to add to the list?");
				currentNumber = Integer.parseInt(myScanner.next());
				System.out.println("Where would you like to add the item?");
				response = myScanner.next();
				try {
					int index = Integer.parseInt(response);
					myArrayList.add(currentNumber, index);
				} catch(NumberFormatException e) {
					System.out.println("Looks like we'll add the item to the end of the list.");
					myArrayList.add(currentNumber);
				}
			} else if(response.equalsIgnoreCase("get")) {
				try {
					System.out.println("What index would you like to get?");
					currentNumber = Integer.parseInt(myScanner.next());
					System.out.println("The item at index " + currentNumber 
						+ " is " + myArrayList.getItem(currentNumber));
				} catch(NumberFormatException e){
					System.out.println("Sorry but you entered a value that couldn't "
						+ "be interpreted as a number");
				}
			} else if(response.equalsIgnoreCase("size")) {
				System.out.println("There are currently " + myArrayList.getSize() 
					+ " items in the list");
			} else if(response.equalsIgnoreCase("remove")){ 
				try {
					System.out.println("What is the item to be removed?");
					currentNumber = Integer.parseInt(myScanner.next());
					String saying = myArrayList.remove(currentNumber) ? "Successfully removed number!" :
					"Couldn't remove the number! ):";
					System.out.println(saying);
				} catch(NumberFormatException e) {	
					System.out.println("Sorry but that's not an item that I recognize! >:(");
				}
			}
			System.out.println(myArrayList.toString());
		}
	}
}