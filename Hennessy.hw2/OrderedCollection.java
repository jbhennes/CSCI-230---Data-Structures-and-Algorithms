/**
* 
* OrderedCollection stores a collection of Comparable (in an array), along
* with the current size of the collection. *
* Elements ordered in ascending order according to the Comparable stored *
* @author Jake Hennessy
* @since Jan 27 2015
* 
*/

//Declaring package
package edu.cofc.csci230;

//Class declaration
public class OrderedCollection<T extends Comparable<? super T>> { 
	
	//Instance Variables
	private T collection[]; // the collection 
	private int size; // how many elements currently stored
	private int totalCap; //Actual capacity of the array

/**
* Constructor allocates array and initializes size 
* @param size the number of elements stored
*/

	public OrderedCollection (int capacity) {
		collection = (T[]) new Comparable[capacity];
		size = 0;
		totalCap = capacity;
		}
	
/**
 * isEmpty()
 * 
 * Precondition: the collection exists
 * Postcondition: the method will return a value of true or false depending on if the array is populated with values
 */
	public Boolean isEmpty() {
		
		//Condition to test for the size, if size = 0, the OrderedList is empty.
		if (this.size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

/**
* makeEmpty() 
* 
* Precondition: the method must be passed a collection that has been instantiated 
* Postcondition: after the method the ordered collection will be empty of all elements
*/
	public void makeEmpty() {
		
		//Loop to move through the OrderedList element by element
		for ( int i = 0; i < this.size; i++ ) {
			
			//Setting each element back to null.
			this.collection[i] = null;
		}
		//resetting the size variable
		this.size = 0;
		
		//Calling the halfCapacity method to shrink the empty list's capacity
		this.halfCapacity(this.collection);
		
	}
	
/**
 * doubleCapacity()
 * 
 * Precondition: the method will accept an orderedList.
 * Postcondition: provided that the size is equal to the capacity of the orderedList,
 *  the list will be copied to an array that will be double the capacity.
 */
	public OrderedCollection<T> doubleCapacity( T[] lst ) {
		
		//Instantiating a new list with twice the capacity
		OrderedCollection<T> doubleCap = new OrderedCollection<>(this.totalCap * 2);
		//Using arrayCopy to copy the contents of the first list to the larger capacity list
		System.arraycopy(this.collection, 0, doubleCap.collection, 0, this.size);
		
		//Changing the instance variable values to reflect the new list.
		this.collection = doubleCap.collection;
		this.totalCap = doubleCap.totalCap;
		return this;
	}
	
/**
 * doubleCapacity()
 * 
 * Precondition: the method will accept an orderedList.
 * Postcondition: provided that the size is equal to the capacity of the orderedList,
 *  the list will be copied to an array that will be double the capacity.
 */
	public OrderedCollection<T> halfCapacity( T[] lst ) {
			
		//Instantiating a new list with half the capacity
		OrderedCollection<T> halfCap = new OrderedCollection<>(this.totalCap / 2);
		//Using arrayCopy to copy the contents of the first list to the smaller capacity list
		System.arraycopy(this.collection, 0, halfCap.collection, 0, this.size);
			
		//Changing the instance variable values to reflect the new list.
		this.collection = halfCap.collection;
		this.totalCap = halfCap.totalCap;
		return this;
	}
	
/**
 * insert()
 * 
 * Precondition: There must be an object that will be placed into the collection, and the collection must exist
 * 					Also, if the object already exists in the list, it will not be placed into the list again.
 * Postcondition: The object will be inserted into the collection (provided the object to be inserted isn't in the list)
 * 
 * @param x, the element to add to the collection
 */
	public void insert( T x ) {
		
		//Checking to see if the element passed into the method already exists in the list.
		for (int i = 0; i < this.size; i++ ) {
			//Using compareTo to check if the element passed is equal to the element at position i
			if (this.collection[i].compareTo(x) == 0 ) {
				return;
			}
		}
		//Checking to see if we need to call the doubleCapacity method to expand the list.
		if (this.size >= this.totalCap) {
			this.doubleCapacity(this.collection);
		}
		//Now, we actually insert the item into the list.
		this.collection[size] = x;
		//Increment the size by + 1
		this.size++;
		//Call the sort() method to put the list in order
		this.sort(this.collection);
	}
	
/**
 * sort()
 * 
 * Precondition: accepts an orderedList.
 * Postcondition: the orderedList that was passed as an argument will be returned sorted. (BUBBLE SORT . . . UGH)
 * 
 * @param lst, the orderedList that needs to be sorted.
 */
	public T[] sort( T[] lst ) {
		
		//Iterating through the list to access each element
		for (int i = 0; i < this.size - 1; i++ ) {
			//Setting an index variable to be able to access outside the loop
			int index = i;
			//Setting up the nested loop to compare each element, performing a bubble sort 
			for (int j = i + 1; j < this.size; j++ ) {
				//Logic (using compareTo()) to see if we need to change the index to move the items around
				if ( this.collection[j].compareTo(this.collection[i]) > 0) {
					index = j;
				}
			}
			//Final portion of the sort that uses the temp variable to swap values.
			T temp = this.collection[index];
			this.collection[index] = this.collection[i];
			this.collection[i] = temp;
		}
		//returning the sorted collection to the user.
		return this.collection;
	}
	
/**
 * remove()
 *  
 * Precondition: The collection must exist, and the object must be contained within the collection
 * Postcondition: The object will be removed from the collection.
 * 
 * @param x, which is the element to remove
 */
	public void remove( T x ) /*throws ItemNotFoundException*/ {
		
		
		//Initializing the index variable to use in the second loop
		int index = -1;
		//Iterating through the list
		for (int i = 0; i < this.size; i++ ) {
			//Checking to see if the element to remove is the element at the index in question
			if (this.collection[i].compareTo(x) == 0) {
				//setting the index variable to where the element to be removed was in the list
				index = i;
			}
		}
		if ( index == -1 ) {
			System.out.println("The item " + x + " was not found in the list!\n");
			return;
		}
		
		// Sliding all of the list indices -1 from the index position ( in argument ) to the end of the list. 
		for ( int i = index + 1; i < size; i++ ) {
			// The actual slide move that is repeated throughout the loop
            this.collection[i - 1] = this.collection[i];
		}
		//Decrementing size
		this.size--;
		
		//Finally checking to see if the size of the list <= half of the list's capacity
		if (this.size <= (this.totalCap / 2)) {
			
			//If the condition is met, we call the halfCapacity method to shorten the capacity of the list
			this.halfCapacity(this.collection);
		}
	}
	
/**
 * findMin()
 * 
 * Precondition: The collection must exist
 * Postcondition: the smallest object should be returned to the user
 */
	public T findMin() {
		
		//Checking to make sure the list is not empty
		if (this.size > 0) {
			//List is in order, so the last element is the minimum.
			return this.collection[this.size - 1];
		}
		return null;
	}
	
/**
 * findMax()
 * 
 * Precondition: The collection must exist
 * Postcondition: The largest object will be returned to the user.
 */
	public T findMax() {
		//Checking to make sure the list is not empty
		if (this.size > 0) {
			//List is in order, so the first element is the maximum
			return this.collection[0];
		}
		return null;
	}
	
/**
 * toString()
 * 
 * Precondition: The collection must exist for the method to be used.
 * Postcondition: Returns a string representation of the list.
 */
	public String toString() {
		
		//Creating the string message
		String message = "";
		
		//Testing to see if the size = 0, and changing the message
		// to avoid a null pointer exception
		if ( this.size == 0 ) {
			message = "The OrderedList is empty!";
			message += "\ntotal size = " + this.size + "\ntotal capacity = " + this.totalCap + "\n";
			return message;
		}
		
		//If size of list > 0, then we iterate through it, print the contents, size, and capacity.
		for (int i = 0; i < size; i++) {
			
			//Logic to format the output string with commas, except for the last element.
			if (i == (size - 1)) {
				message += this.collection[i].toString();
			}
			else {
				message += this.collection[i].toString() + ", ";
			}
		}
		
		//Inserting the size and capacity info into the string as well
		message += "\ntotal size = " + this.size + "\ntotal capacity = " + this.totalCap + "\n";
		return message;
	}

	}// end class OrderedCollection
