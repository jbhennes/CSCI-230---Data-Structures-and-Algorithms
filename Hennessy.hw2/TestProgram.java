/**
* 
* Main program to test the implementation of OrderedCollection, 
* which will create the collection and test the various methods defined in the
* OrderedCollection class file.
* 
* @author Jake Hennessy
* @since Jan 27 2015
* 
*/

package edu.cofc.csci230;

public class TestProgram {

	public static void main(String[] args) {
		
		//Testing the constructors by creating two ordered lists.
		OrderedCollection<Integer> a = new OrderedCollection<>(5);
		OrderedCollection<Double> b = new OrderedCollection<>(5);
		
		//Testing isEmpty() method
		System.out.println(a.isEmpty()); //TRUE
		System.out.println(b.isEmpty()); //TRUE
		System.out.println();
		
		// Testing the insert method.
		a.insert(4);
		b.insert(67.32);
		a.insert(345);
		b.insert(Math.sqrt(2.00));
		a.insert(987);
		b.insert(Math.PI);
		a.insert(-12);
		b.insert(-1.23);
		a.insert(1200);
		b.insert(65*-7.3);
		
		//Printing the contents of the collection.
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		//Now with five elements in each, we will add another element 
		// to test the doubling capacity of the array.
		a.insert(314);
		b.insert(13.0);
		
		//Testing the insert method's handling of duplicates
		// by trying to insert all of the elements a second time.
		// (The collection should not change)
		a.insert(314);
		b.insert(13.0);
		a.insert(4);
		b.insert(67.32);
		a.insert(345);
		b.insert(Math.sqrt(2.00));
		a.insert(987);
		b.insert(Math.PI);
		a.insert(-12);
		b.insert(-1.23);
		a.insert(1200);
		b.insert(65*-7.3);
		
		//Again, testing isEmpty() method
		System.out.println(a.isEmpty()); //FALSE
		System.out.println(b.isEmpty()); //FALSE
		System.out.println();
		
		//Again, Printing the contents of the collection.
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		//Testing the findMin() method
		System.out.println("Minimum of a = " + a.findMin());
		System.out.println("Minimum of b = " + b.findMin());
				
		//Testing the findMax() method
		System.out.println("Maximum of a = " + a.findMax());
		System.out.println("Maximum of b = " + b.findMax());
		
		//Testing the makeEmpty() method.
		a.makeEmpty();
		b.makeEmpty();
		
		//Again, testing isEmpty() method
		System.out.println(a.isEmpty()); //TRUE
		System.out.println(b.isEmpty()); //TRUE
		System.out.println();
		
		//Printing the contents of the collection.
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		//Now we must re-populate the OrderedList
		a.insert(-23);
		b.insert(3.45);
		a.insert(342);
		b.insert(-56.23);
		a.insert(78 * 89);
		b.insert(Math.PI * -1);
		a.insert(67 - 32);
		b.insert(Math.sqrt(2.00));
		a.insert(789);
		b.insert(Math.E);
		a.insert(109876);
		b.insert(67.98 * 98.2);
		a.insert(2);
		b.insert(12200.3);
		
		//Printing the collection again after it has been re-populated.
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		//Remove Method
		a.remove(2);
		b.remove(Math.E);
		
		//Printing the contents of the altered collection.
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		//Removing to test halfCapacity method.
		a.remove(109876);
		b.remove(67.98 * 98.2);
		
		//Removing something that doesn't exist!
		a.remove(0);
		b.remove(0.0);
		
		//Printing the contents of the altered collection, and to see if the capacity is half of what it was
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		//Testing the findMin() method
		System.out.println("Minimum of a = " + a.findMin());
		System.out.println("Minimum of b = " + b.findMin());
		
		//Testing the findMax() method
		System.out.println("Maximum of a = " + a.findMax());
		System.out.println("Maximum of b = " + b.findMax());

	}

}
