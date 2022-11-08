package com.todo.exception;

public class UserAlreadyExistsException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1234234217L;

	public UserAlreadyExistsException() {
		 
	        super("The user already exists!");
	    }
	
} 
