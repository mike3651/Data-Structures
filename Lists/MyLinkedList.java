/** 
 * @author Michael Wilson
 * @version 1.0 */

public class MyLinkedList {
	/** Reference to the front of the list */
	private Node front;

	/** Keeps track of the size of the list */
	private int size;

	/** Creates an empty linked list */
	public MyLinkedList() {
		front = null;
	}

	/** 
	 * Method that adds to the front of the list	
	 *
	 * @param data The data of the node to add to the list */
	public void addToFront(final int data) {
		add(data, 0);
	}

	/** 
	 * Method that adds a node at the end of the list 
	 *
	 * @param data The data of the node to add to the list */
	public void append(final int data) {
		if(front == null) {
			front = new Node(data);
		} else {
			Node current = front;
			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(new Node(data));
		}		
		size++;
	}

	/** 
	 * Method that adds a node at the specific spot 
	 * 
	 * @param data The data of the node to be added to the list 
	 * @param index The location to place the node */
	public void add(final int data, final int index) {		
		if(index <= size) {
			if(index == 0) {
				if(front == null) {					
					front = new Node(data);
				}else {
					Node temp = new Node(data);
					temp.setNext(front);
					front = temp;
				}				
			} else {
				Node current = front;
				// find the node before the position 
				for(int i = 0; i < index - 1; i++) {
					current = current.getNext();
				}
				if(current.getNext() != null) {
					Node temp = new Node(data);
					temp.setNext(current.getNext());
					current.setNext(temp);
				} else {
					current.setNext(new Node(data));
				}
			}			
			size++;
		}
	} 

	/** 
	 * Removes a node from the list 
	 * 
	 * @param data The data the identifies the node, removes the first instance
	 * @return True if successful, false otherwise */
	public boolean remove(final int data) {
		if(front == null) {
			return false;
		} 
		if(front.getData() == data) {
			front = front.getNext();
			size--;
			return true;
		}

		Node current = front.getNext();
		Node previous = front;
		while(current != null) {
			if(current.getData() == data) {				
				System.out.println("made it here");
				previous.setNext(current.getNext());
				size--;			
				return true;
			}
			previous = current;
			current = current.getNext();
		}		
		return false;
	}

	/** 
	 * Gets the node at the given index 
	 * 
	 * @param index The index to find 
	 * @return null if the node is not found, otherwise we return the string rep of the node */
	public String get(final int index) {
		if(index >= size) {
			return null;
		} else {
			Node current = front;
			for(int i = 0; i < index; i++) {
				current = current.getNext();
			}
			return current.toString();
		}
	}

	/** 
	 * Gets the size of the list 
	 * 
	 * @return The size of the list */
	public int getSize() {
		return size;		
	}

	/** Clears the list */
	public void clear() {
		front = null;
		size = 0;
	}

	/** 
	 * Gets the string version of the list
	 * 
	 * @return The string representation of this list */
	public String toString() {		
		if(size == 0) {
			return "[]";
		}
		StringBuilder myString = new StringBuilder();
		myString.append("[");

		// use pointer to make sure that we don't lose track of our nodes 
		Node current = front;
		myString.append(front.getData());
		
		// loop through the linked list
		while(current.getNext() != null) {			
			myString.append(", ");
			myString.append(current.getNext().getData());
			current = current.getNext();
		}
		myString.append("]");
		return myString.toString();
	}


	/** Class which is only accessible to the outer class */
	private class Node {		
		/** Data field */
		private int data;

		/** Next field reference */
		private Node next;

		/** 
		 * Creates a new node
		 *
		 * @param data The information to store in this node */
		public Node(final int data) {
			this(data, null);
		}

		/** 
		 * Creates a new node, overloaded constructor
		 *
		 * @param data The information to store in this node
		 * @param next The reference to the node after this one */
		public Node(final int data, final Node next) {
			this.data = data;
			this.next = next;
		}

		// GETTERS AND SETTERS

		/** 
		 * Gets the data of this node
		 *
		 * @return The data of this node */
		public int getData() {
			return data;
		}

		/** 
		 * Gets the next node in the chain 
		 *
		 * @return The node after this one */
		public Node getNext() {
			return next;
		}

		/** 
		 * Allows the user to modify the next link of this node 
		 *
		 * @param next The next node to be set in this chain */
		public void setNext(final Node next) {
			this.next = next;
		}

		/** 
		 * String representation of the node 
		 * 
		 * @return String version of the node */
		public String toString() {
			return "{ data: " + this.data + ", next: " + this.next + "}";
		}
	}
}