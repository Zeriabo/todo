package com.todo.exception;

public class TodoNotFoundException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1234234216L;

	public TodoNotFoundException() {
		 
	        super("The Todo is not found!");
	    }
	
} 
