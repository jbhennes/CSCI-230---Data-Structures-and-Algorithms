/**
 * 
 * @author Jacob Hennessy, Mark Allen Weiss
 * @since Feb, 24 2015, Mar. 11 2015.
 *
 * Purpose: This file contains a class for a binary search tree, with methods that will allow for insertion
 * 	and removal of nodes, and to find max and minimum nodes.
 * 
 * NOTE: This file contains large amounts of code from Mr. Mark Allen Weiss, but a test program written by myself, 
 * 	and a shuffle and time method that were found via stackoverflow forums.
 */

//Package declaration
package edu.cofc.csci230;

//Importing the java utils
import java.util.*;


// BinarySearchTree class provided by Weiss
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x, replacing it with largest
//                              value in right subtree, if x has two children
// void removeL(x)        --> Remove x, replacing it with smallest value
//                              value in left subtree, if x has two children
// void removeAlternating(x)
//                        --> Remove x alternating between routines remove and
//                              removeL
// void removeRandomly(x) -->  Remove x randomly choosing between routines remove and
//                              removeL
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// void displayTree( )   --> Print tree in level-order
// void removeL(x)       --> Remove x, 
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss (original)
 * @author McCauley  Updated to add methods
 *    displayTree, removeL, removeAlternating, removeRandomly
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * If x is in a node with two children, x will be replaced by
     * the smallest value in the its right subtree.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }
    /**
     * Remove from the tree. Nothing is done if x is not found.
     * If x is in a node with two children, x is replaced by the largest
     * value in its left subtree.
     * @param x the item to remove.
     */
    public void removeL( AnyType x )
    {
        root = removeL( x, root );
    }
    /**
     * Remove from the tree. Nothing is done if x is not found.
     * The subtree from which x's replacement comes alternates each time
     * this method is called.
     * @param x the item to remove.
     */
    private Boolean removeFromRight = true;
    public void removeAlternating( AnyType x )
    {
        if (removeFromRight)
            root = remove( x, root );
        else
            root = removeL( x, root );
        removeFromRight = !removeFromRight;
    }
        /**
     * Remove from the tree. Nothing is done if x is not found.
     * The subtree from which x's replacement comes is chosen at random.
     * @param x the item to remove.
     */ private Boolean removeRight;
    public void removeRandomly( AnyType x )
    { 
        Boolean removeRight = Math.round(Math.random()+1) == 1;
        if (removeRight)
           root = remove( x, root );
        else
            root = removeL(x, root); 
    }
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    /**
     * Shuffles an array of numbers randomly, using the Fisher-Yates shuffle.
     * Credit goes to stackoverflow.com/questions/1519736/random-shuffling-of-an-array
     * @param itemList, an array of integers.
     */
    static void shuffleArray( int[] itemList ) {
    	//Creating a new random number generator
    	Random rnd = new Random();
    	//
    	for (int i = (itemList.length - 1); i > 0; i-- ) {
    		int index = rnd.nextInt(i + 1);
    		// A quick swap using listMarker as a temp variable
    		int listMarker = itemList[index];
    		itemList[index] = itemList[i];
    		itemList[i] = listMarker;
    		}
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *    Specifically, if the value to be removed is in a node with
     *    two children, this method removes the value by replacing it with
     *    the smallest value in the "to be removed" nodes right subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null ) { // Item not found; do nothing
        	count++;
            return t;
        }
            
        int compareResult = x.compareTo( t.element );
        count++;
            
        if( compareResult < 0 ) {
        	count++;
            t.left = remove( x, t.left );
        }
        else if( compareResult > 0 ) {
        	count++;
            t.right = remove( x, t.right );
        }
        else if( t.left != null && t.right != null ) // Two children
        {
        	count++;
            t.element = findMin( t.right ).element;
            count++;
            t.right = remove( t.element, t.right );
        }
        else {
        	count++;
            t = ( t.left != null ) ? t.left : t.right;
        }
        return t;
    }


     /**
     * Internal method to remove from a subtree.
     *   Specifically, if the value to be removed is in a node with
     *    two children, this method removes the value by replacing it with
     *    the largest value in the "to be removed" nodes left subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> removeL( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null ) {
        	count++;
            return t;   // Item not found; do nothing
        }
            
        int compareResult = x.compareTo( t.element );
        count++;
            
        if( compareResult < 0 ) {
        	count++;
            t.left = remove( x, t.left );
        }
        else if( compareResult > 0 ) {
        	count++;
            t.right = remove( x, t.right );
        }
        else if( t.left != null && t.right != null ) // Two children
        {
        	count++;
            t.element = findMax( t.left ).element;
            count++;
            t.left = removeL( t.element, t.left );
        }
        else {
        	count++;
            t = ( t.left != null ) ? t.left : t.right;
        }
        return t;
    }
    
    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null ) {
        	count++;
            return null;
        }
        else if( t.left == null ) {
            count++;
        	return t;
        }
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null ) {
            while( t.right != null ) {
            	count++;
                t = t.right;
            }
        }
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
  /**
   * Output all nodes, level by level
   * 
   */ 
  public void displayTree()
  {
      System.out.println("<<< THE TREE CONTAINS:\n");
      displayLevel(root);
  }
  
  /** 
   * Output all nodes, level by level
   * @ param node - the node that roots the current subtree
   */
  private void displayLevel(BinaryNode node) {
      if (node == null) {
         System.out.print(" - ");
      }
      else {
        //Queue that holds the nodes on the current level
        Queue<BinaryNode> thisLevel = new LinkedList<BinaryNode>();
        //Queue that holds the nodes on the next level
        Queue<BinaryNode> nextLevel = new LinkedList<BinaryNode>(); 
        //put the root on the current level's queue
        thisLevel.add(node);
        int h = height(root);
        int levelTotal = 0;  //keeps track of the total levels printed so we don't  pass the height and print a billion "null"s
        while(!thisLevel.isEmpty()&& (levelTotal<= h))
        {

            while (!thisLevel.isEmpty()) // print nodes at current level
            {
               if (thisLevel.peek() != null)
               {
                  System.out.print(thisLevel.peek().element + " ");
                  // if there is a left pointer , put on next level; if none, put a null
                  node = thisLevel.peek().left; // look at the left pointer of node
                  nextLevel.add(node); 
                  node = thisLevel.remove().right; // get right pointer and remove node
                  nextLevel.add(node); 
               }
               else
               {
                  System.out.print(" - ");
                  nextLevel.add(null); 
                  nextLevel.add(null);
                  thisLevel.remove();
               }
            }  // end while (!thisLevel.isEmpty())
            // Moving to the next level, copy nodes to thisLevel so we know to 
            // advance level count and line in output
            while (!nextLevel.isEmpty())
            {
                  thisLevel.add(nextLevel.remove());
            } // end while (!nextLevel.isEmpty()
            System.out.println(); 
            levelTotal++;
        } // end while(!thisLevel.isEmpty()&& (levelTotal<= h))
      System.out.println(); // an extra line feed to separate this printout of from next
    }
  }
  
  /**
   * timerStart takes a snapshot of the system clock.
   * @return start, a double that represents the system clock when the method was invoked
   * 
   * Note: Credit for this method should go to stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
   */
  public double time() {
	  //Creating a double by doing a cast to the System's own nanoTime() method.
	  double time =  System.nanoTime();
	  return time;
  }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;
    static int count = 0;


    
    
        // Test program
    public static void main( String [ ] args )
    {
    	
    	//First, lets create an array of integers of length 100.
    	//int[] intArray = {3, 8, 11, 13, 18, 21, 26, 29, 34, 37, 45, 48, 51, 57, 65, 69, 71, 74, 80, 89}; //FOR TESTING
    	int[] intArray = new int[1000];
    	for (int i = 0; i < intArray.length; i++) {
    		//Multiplying the random decimals (0 < number < 1) by 100 and rounding them for integers
    		intArray[i] = Math.round((float) (Math.random() * 100));
    	}
    	//Sorting the array to print out in order, it gives a better idea of the number distribution
    	Arrays.sort(intArray);
    	
    	
    	//Printing the Array
    	System.out.println("First, the array goes as follows:");
    	for (int i = 0; i < intArray.length; i++ ) {
    		if (i == intArray.length - 1) {
        		System.out.print(intArray[i] + "\n\n");
    		}
    		else {
    			System.out.print(intArray[i] + ", ");
    		}
    	}
    	
    	
    	//Now the array is created, we will randomize the order of the integers
    	// ******* NOTE ********
    	// The below code references a static method that I implemented after looking up how to
    	// randomize (shuffle) an ordered array. It uses what is called the 'Fisher-Yates shuffle'.
    	// ** (More documentation appears in the method header.) **
    	shuffleArray(intArray);
    	
    	
    	// printing the array to prove that it is indeed shuffled.
    	System.out.println("Now, the shuffled array goes as follows:");
    	for (int i = 0; i < intArray.length; i++) {
    		
    		if (i == intArray.length - 1) {
        		System.out.print(intArray[i] + "\n\n");
    		}
    		else {
    			System.out.print(intArray[i] + ", ");
    		}
    	}
    	System.out.println();
    	
    	
    	//Now we will begin to insert nodes into new BSTs.
    	//First we will create the BST that will use left, right, alternating, and random removals 
    	// in that order
    	BinarySearchTree<Integer> tLeft = new BinarySearchTree<>();
    	BinarySearchTree<Integer> tRight = new BinarySearchTree<>();
    	BinarySearchTree<Integer> tAlt = new BinarySearchTree<>();
    	BinarySearchTree<Integer> tRan = new BinarySearchTree<>();
    	
    	
    	// We will now populate the tree with our array values
    	System.out.println("Now we will begin to populate the binary search trees using the integers in the array:\n");
    	for (int i = 0; i < intArray.length; i++ ) {
    		System.out.println("<<< INSERTING: " + intArray[i]);
    		tLeft.insert(intArray[i]);
    		tRight.insert(intArray[i]);
    		tAlt.insert(intArray[i]);
    		tRan.insert(intArray[i]);
    	}
    	
    	
    	//Next we will display all of the binary trees.
    	System.out.println("\nNext, we are printing out all of the trees to confirm they are all the same and the code is working:\n");
    	System.out.println("\n##############\nBST (RIGHT REMOVAL)\n###############\n");
    	tRight.displayTree();
    	System.out.println("\n##############\nBST (LEFT REMOVAL)\n###############\n");
    	tLeft.displayTree();
    	System.out.println("\n##############\nBST (ALTERNATING REMOVAL)\n###############\n");
    	tAlt.displayTree();
    	System.out.println("\n##############\nBST (RANDOM REMOVAL)\n###############\n");
    	tRan.displayTree();
    	
    	
    	//Next we will again shuffle the intArray, in order to achieve random ordering of values
    	shuffleArray(intArray);
    	
    	
    	//Now we need to begin removing nodes, first we will use the Left remove() method.
    	System.out.println("\nNow we will test the remove methods, for each type of remove we will:\n\t>Print the element to be removed\n\t>Print the tree after the removal\n\t>Print the total time for the BST to be empty\n");
    	System.out.println("\n##############\nUSING ONLY THE RIGHT REMOVE METHOD:\n###############\n");
    	double startR = tRight.time();
    	for (int i = 0; i < intArray.length; i++ ) {
    		System.out.println("<<< REMOVING RIGHT: " + intArray[i]);
    		double loopStart = tRight.time();
    		tRight.remove(intArray[i]);
    		double loopTime = tRight.time() - loopStart;
    		tRight.displayTree();
    		System.out.println("\nTime for this remove: " + loopTime + " nanoseconds.\nTotal instruction count: " + count + "\n");
    	}
    	double timeR = tRight.time() - startR;
    	System.out.println("\n\n====\n\tTOTAL TIME: " + (timeR / 1000000000) + " seconds.\n\t\t(" + timeR + " nanoseconds.)\n====\n");
    	System.out.println("The total number of operations for this method of removal: " + count);
    	//Binding the count value
    	int rightCount = count;
    	
    	//Reset count
    	count = 0;
    	
    	//Next we will use the Right remove() method on that particular tree.
    	System.out.println("\n##############\nUSING ONLY THE LEFT REMOVE METHOD:\n###############\n");
    	double startL = tLeft.time();
    	for (int i = 0; i < intArray.length; i++ ) {
    		System.out.println("<<< REMOVING LEFT: " + intArray[i]);
    		double loopStart = tLeft.time();
    		tLeft.removeL(intArray[i]);
    		double loopTime = tLeft.time() - loopStart;
    		tLeft.displayTree();
    		System.out.println("\nTime for this remove: " + loopTime + " nanoseconds.\nTotal instruction count: " + count + "\n");
    	}
    	double timeL = tLeft.time() - startL;
    	System.out.println("\n\n====\n\tTOTAL TIME: " + (timeL / 1000000000) + " seconds.\n\t\t(" + timeL + " nanoseconds.)\n====\n");
    	System.out.println("The total number of operations for this method of removal: " + count);
    	//Binding the count value
    	int leftCount = count;
    	
    	//reset count
    	count = 0;
    	
    	//Next we will use the alternating remove() method on its particular tree.
    	System.out.println("\n##############\nUSING ONLY THE ALTERNATING REMOVE METHOD:\n###############\n");
    	double startAlt = tAlt.time();
    	for (int i = 0; i < intArray.length; i++ ) {
    		System.out.println("<<< REMOVING ALTERNATING: " + intArray[i]);
    		double loopStart = tAlt.time();
    		tAlt.removeAlternating(intArray[i]);
    		double loopTime = tAlt.time() - loopStart;
    		tAlt.displayTree();
    		System.out.println("\nTime for this remove: " + loopTime + " nanoseconds.\nTotal instruction count: " + count + "\n");
    	}
    	double timeAlt = tAlt.time() - startAlt;
    	System.out.println("\n\n====\n\tTOTAL TIME: " + (timeAlt / 1000000000) + " seconds.\n\t\t(" + timeAlt + " nanoseconds.)\n====\n");
    	System.out.println("The total number of operations for this method of removal: " + count);
    	//Binding the count value
    	int altCount = count;
    	
    	//Reset count
    	count = 0;
    	
    	//Next we will use the random remove() method on the last tree.
    	System.out.println("\n##############\nUSING ONLY THE RANDOM REMOVE METHOD:\n###############\n");
    	double startRan = tRan.time();
    	for (int i = 0; i < intArray.length; i++ ) {
    		System.out.println("<<< REMOVING RANDOM: " + intArray[i]);
    		double loopStart = tRan.time();
    		tRan.removeRandomly(intArray[i]);
    		double loopTime = tRan.time() - loopStart;
    		tRan.displayTree();
    		System.out.println("\nTime for this remove: " + loopTime + " nanoseconds.\nTotal instruction count: " + count + "\n");
    	}
    	double timeRan = tRan.time() - startRan;
    	System.out.println("\n\n====\n\tTOTAL TIME: " + (timeRan / 1000000000) + " seconds.\n\t\t(" + timeRan + " nanoseconds.)\n====\n");
    	System.out.println("The total number of operations for this method of removal: " + count);
    	//Binding the count value
    	int randCount = count;
    	
    	System.out.println("\n\t ==== TOTALS ====");
        System.out.println("Right ==> Time: " + (timeR) + " ns, Count: " + rightCount);
        System.out.println("Left ==> Time: " + (timeL) + " ns, Count: " + leftCount);
        System.out.println("Alternating ==> Time: " + (timeAlt) + " ns, Count: " + altCount);
        System.out.println("Random ==> Time: " + (timeRan) + " ns, Count: " + randCount);
    }
}
    	
    	
// PREVIOUS HOMEWORK CODE BELOW

//
////importing the utils so that we may use comparable
//import java.util.*;
//
////class declarations
//public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
//
////Instance variables created when a BinarySearchTree object is created.
//private BinaryNode<AnyType> root;
//private int level;
//private Comparator<? super AnyType> cmp;
//
///**
// * =============================================
// * ========= INNER CLASS - BINARY NODE =========
// * =============================================
// */
////Binary Node Class declaration
//private static class BinaryNode<AnyType> {
//	
//	//Constructors
//	BinaryNode( AnyType theElement ) {
//		this( theElement, null, null );
//	}
//	
//	//Second constructor that is set with left and right children.
//	//If those children are null, the above constructor is what is created.
//	BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt ) {
//		element = theElement;
//		left = lt;
//		right = rt;
//		level = 0;
//		
//	}
//	
//	//Instance Variables for the BinaryNode class
//	AnyType element;
//	BinaryNode<AnyType> left;
//	BinaryNode<AnyType> right;
//	int level;
//}
//
///**
// * ================================
// * ========== END CLASS ===========
// * ================================
// */
//
///**
// * Constructor that created a null tree with the root pointer pointing to null.
// * 
// */
//public BinarySearchTree() {
//	this(null);
//}
//
///**
// * Constructor that creates a BST that has a null root, but a comparator parameter that is
// * 	used when we need to actually insert ot a tree because it needs to be compared to
// * 	other values in the tree.
// * 
// * @param c
// */
//public BinarySearchTree( Comparator<? super AnyType> c ) {
//	root = null;
//	cmp = c;
//}
//
///**
// * makeEmpty()
// * 
// * This method will take the existing BST and make clear it by essentially making the root
// * 	node null. 
// * 
// */
//public void makeEmpty() {
//	root = null;
//}
//
///**
// * isEmpty()
// * 
// * This method will test to see if the root node is null, if it is it will return true, otherwise it will
// * 	return false.
// * 
// * @return
// */
//public boolean isEmpty() {
//	return ( root == null );
//}
//
///**
// * contains()
// * 
// * this method is designed to check and see whether or not the tree that we are searching through 
// * 	actually contains the value that we are searching for
// * 
// * @param x
// * @return
// */
//public boolean contains( AnyType x ) {
//	return contains( x, root );
//}
//
///**
// * findMin()
// * 
// * This method looks at the tree that is calling it, and searches recursively through the tree
// * 	 (on the left side) to find the minimum element in the tree.
// * 
// * @return
// */
//public AnyType findMin() {
//	if (isEmpty()) {
//		throw new UnderflowException();
//	}
//	else {
//		return findMin( root ).element;
//	}
//}
//
///**
// * findMax()
// * 
// * This method is designed to look at the tree that is calling it, and search through the tree (the
// * 	right side of the tree) to find the max element!
// * 
// * @return
// */
//public AnyType findMax() {
//	if (isEmpty()) {
//		throw new UnderflowException();
//	}
//	else {
//		return findMax( root ).element;
//	}
//}
//
///**
// * insert()
// * 
// * This method actually places new nodes into the tree, this method actually adds to the right hand subtree
// * 	and does not return anything.
// * @param x
// */
//public void insert( AnyType x ) {
//	root = insert( x, root );
//}
//
///**
// * remove()
// * 
// * This method removes a node of your choice, all that needs to be submitted is the value, 
// * 	which will then be removed from the right hand side of the tree, by taking the least 
// * amount from the tree two levels down, and then returning that value as the new root.
// * 
// * @param x
// */
//public void remove( AnyType x ) {
//	root = remove( x, root );
//}
//
///**
// * removeL()
// * 
// * This method removes a node of your choice, all that needs to be submitted is the value, 
// * 	which will then be removed from the left hand side of the tree, by taking the greatest 
// * amount from the tree two levels down, and then returning that value as the new root.
// * 
// * @param x
// */
//public void removeL( AnyType x ) {
//	root = removeL( x, root );
//}
//
///**
// * removeAlternating()
// * 
// * this method uses the remove and removeL method and the height of the node in question, 
// * 	and divides it by two, taking the remainder and using that to determine whether or not to use remove()
// * 	or removeL()
// * 
// * @param x
// */
//public void removeAlternating( AnyType x ) {
//	root = removeAlternating( x, root );
//}
//
///**
// * removeRandomly()
// * 
// * this method uses a random float that is multiplied by 10 and divided by two, taking the remainder as our
// * 	value that will be tested as to wheter or not to go right or left.
// * @param x
// */
//public void removeRandomly( AnyType x ) {
//	root = removeRandomly( x, root );
//}
//
///**
// * printTree()
// * 
// * This method will print the tree in a depth first search fashion so that the tree is printed in 
// * 	nondecreasing order.
// * 
// */
//public void printTree() {
//	
//	if (isEmpty()) {
//		System.out.println("Empty tree!");
//	}
//	else {
//		printTree( root );
//	}
//}
//
///**
// * displayTree()
// * 
// * This method does a breadth first search that will essentially print the tree level by level
// * 
// */
//public void displayTree() {
//	
//  	  System.out.println("The tree contains:\n");
//      displayLevel(root);
//      
//  }
//
///**
// * getHeight()
// * 
// * this method will return the value of the level that the particular node is on
// * @return
// */
//public int getHeight() {
//	
//	if (isEmpty()) {
//		System.out.println("Tree is empty!");
//	}
//	else {
//		level = height( root );
//	}
//	return level;
//}
//
///*
//public void inOrderPrintTree() {
//	if (isEmpty()) {
//		System.out.println("Empty tree!");
//	}
//	else {
//		inOrderPrintTree( root );
//	}
//	
//}
//*/
//
///* ==========================================================
// * ================ END OF PUBLIC METHODS ===================
// * ==========================================================
// * ================ BEGIN PRIVATE METHODS ===================
// * ==========================================================
// */
//
///**
// * contains()
// * 
// * this method is designed to check and see whether or not the tree that we are searching through 
// * 	actually contains the value that we are searching for
// * 
// * @param x
// * @param t
// * @return
// */
//private boolean contains( AnyType x, BinaryNode<AnyType> t ) {
//	if (t == null) {
//		return false;
//	}
//	int compareResult = myCompare( x, t.element );
//	
//	if (compareResult < 0) {
//		return contains( x, t.left );
//	}
//	else if (compareResult > 0) {
//		return contains( x, t.right );
//	}
//	else {
//		return true;
//	}
//}
//
///**
// * findMin
// * 
// * This method looks at the tree that is calling it, and searches recursively through the tree
// * 	 (on the left side) to find the minimum element in the tree.
// * 
// * @param t
// * @return
// */
//private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t ) {
//	
//	if (t == null) {
//		return null;
//	}
//	else if (t.left == null) {
//		return t;
//	}
//	return findMin( t.left );
//}
//
///**
// * findMax()
// * 
// * This method is designed to look at the tree that is calling it, and search through the tree (the
// * 	right side of the tree) to find the max element!
// * 
// * @param t
// * @return
// */
//private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t ) {
//	
//	if (t == null) {
//		return null;
//	}
//	else if (t.right == null) {
//		return t;
//	}
//	return findMax( t.right );
//}
//
///**
// * myCompare()
// * 
// * this is a method that was included in the author's package in the book, which is used to compare elements
// * 	that are not necessarily comparables.
// * 
// * @param lhs
// * @param rhs
// * @return
// */
//private int myCompare( AnyType lhs, AnyType rhs ) {
//	if (cmp != null) {
//		return cmp.compare( lhs, rhs );
//	}
//	else {
//		return ((Comparable)lhs).compareTo( rhs );
//	}
//}
//
///**
// * insert()
// * 
// * This method actually places new nodes into the tree, this method actually adds to the right hand subtree
// * 	and does not return anything.
// * @param x
// * @param t
// * @return
// */
//private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t) {
//	
//	if (t == null) {
//		return new BinaryNode<>( x, null, null );
//	}
//	
//	int compareResult = x.compareTo( t.element );
//	
//	if (compareResult < 0) {
//		t.left = insert( x, t.left );
//		t.level += 1;
//	}
//	else if (compareResult > 0) {
//		t.right = insert( x, t.right );
//		t.level += 1;
//	}
//	else {
//		;
//	}
//	return t;
//}
//
///**
// * remove()
// * 
// * This method removes a node of your choice, all that needs to be submitted is the value, 
// * 	which will then be removed from the right hand side of the tree, by taking the least 
// * amount from the tree two levels down, and then returning that value as the new root.
// * 
// * @param x
// * @param t
// * @return
// */
//private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t) {
//	
//	if (t == null) {
//		return t;
//	}
//	
//	int compareResult = x.compareTo( t.element );
//	
//	if (compareResult < 0) {
//		t.left = remove( x, t.left );
//	}
//	else if (compareResult > 0) {
//		t.right = remove( x, t.right );
//	}
//	else if (t.left != null && t.right != null) {
//		t.element = findMin( t.right ).element;
//		t.right = remove( t.element, t.right );
//	}
//	else {
//		t = (t.left != null ) ? t.left : t.right;
//	}
//	return t;
//}
//
///**
// * remove()
// * 
// * This method removes a node of your choice, all that needs to be submitted is the value, 
// * 	which will then be removed from the left hand side of the tree, by taking the greatest 
// * amount from the tree two levels down, and then returning that value as the new root.
// * 
// * @param x
// * @param t
// * @return
// */
//private BinaryNode<AnyType> removeL( AnyType x, BinaryNode<AnyType> t) {
//	
//	if (t == null) {
//		return t;
//	}
//	
//	int compareResult = x.compareTo( t.element );
//	
//	if (compareResult < 0) {
//		t.left = removeL( x, t.left );
//	}
//	else if (compareResult > 0) {
//		t.right = removeL( x, t.right );
//	}
//	else if (t.left != null && t.right != null) {
//		t.element = findMax( t.left ).element;
//		t.left = removeL( t.element, t.left );
//	}
//	else {
//		t = (t.right != null ) ? t.right : t.left;
//	}
//	return t;
//}
//
///**
// * removeAlternating()
// * 
// * this method uses the remove and removeL method and the height of the node in question, 
// * 	and divides it by two, taking the remainder and using that to determine whether or not to use remove()
// * 	or removeL()
// * 
// * @param x
// * @param t
// * @return
// */
//private BinaryNode<AnyType> removeAlternating( AnyType x, BinaryNode<AnyType> t) {
//	
//	if (t == null) {
//		return t;
//	}
//	
//	int compareResult = x.compareTo( t.element );
//	int rightLeft = 0;
//	
//	if (rightLeft % 2 == 1) {
//		t = remove( x, root );
//	}
//	else {
//		t = removeL( x, root );
//	}
//	
//	rightLeft += 1;
//	return t;
//}
//
///**
// * removeRandomly()
// * 
// * this method uses a random float that is multiplied by 10 and divided by two, taking the remainder as our
// * 	value that will be tested as to wheter or not to go right or left.
// * @param x
// * @param t
// * @return
// */
//private BinaryNode<AnyType> removeRandomly( AnyType x, BinaryNode<AnyType> t) {
//	
//	if (t == null) {
//		return t;
//	}
//	
//	int compareResult = x.compareTo( t.element );
//	int rightLeft = (int)(Math.random() * 10);
//	
//	if (rightLeft % 2 == 1) {
//		t = remove( x, root );
//	}
//	else {
//		t = removeL( x, root );
//	}
//	return t;
//}
//
///**
// * printTree()
// * 
// * This method will print the tree in a depth first search fashion so that the tree is printed in 
// * 	nondecreasing order.
// * 
// * @param t
// */
//private void printTree( BinaryNode<AnyType> t ) {
//	
//	if (t != null) {
//		//This prints in an in-order traversal
//		printTree( t.left );
//		System.out.println( t.element );
//		printTree( t.right );
//	}
//}
//
///**
// * getHeight()
// * 
// * this method will return the depth of the node in question. (depth = height)
// * @param t
// * @return
// */
//private int height( BinaryNode<AnyType> t) {
//	if (t == null) {
//		return 0;
//	}
//	else {
//		return 1 + Math.max(height( t.left ), height( t.right ));
//	}
//}
//
//  /** 
//   * displayLevel()
//   * 
//   * This method is designed to print out all of the nodes per level, until it gets to a level 
//   * 	where all of the nodes are null.
//   * 
//   * This code is courtesy of Renee McCauley.
//   */
//  private void displayLevel(BinaryNode node) {
//      if (node == null) {
//         System.out.print(" - ");
//      }
//      else {
//        //Queue that holds the nodes on the current level
//        Queue<BinaryNode> thisLevel = new LinkedList<BinaryNode>();
//        //Queue that holds the nodes on the current level
//        Queue<BinaryNode> nextLevel = new LinkedList<BinaryNode>(); 
//        //put the root on the current level's queue
//        thisLevel.add(node);
//        int h = height(root);
//        int levelTotal = 0;  //keeps track of the total levels printed so we don't  pass the height and print a billion "null"s
//        while(!thisLevel.isEmpty()&& (levelTotal<= h))
//        {
//
//            while (!thisLevel.isEmpty())
//            {
//               if (thisLevel.peek() != null)
//               {
//                  System.out.print(thisLevel.peek().element + " ");
//                  // if there is a left pointer , put on next level; if none, put a null
//                  node = thisLevel.peek().left; // look at the left pointer of node
//                  nextLevel.add(node); 
//                  node = thisLevel.remove().right; // get right pointer and remove node
//                  nextLevel.add(node); 
//               }
//               else
//               {
//                  System.out.print(" - ");
//                  nextLevel.add(null); 
//                  nextLevel.add(null);
//                  thisLevel.remove();
//               }
//            }   
//            while (!nextLevel.isEmpty())
//            {
//                  thisLevel.add(nextLevel.remove());
//            }
//            System.out.println();
//            levelTotal++;
//        }
//      System.out.println();
//    }
//  }
//
//
//
//}

