package edu.cofc.csci230;

public class UnderflowException extends RuntimeException {
	
	public UnderflowException() {
		super();
		System.out.println("No elements exist within the tree!");
	}
}
