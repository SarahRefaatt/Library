package com.example.demo.exception;

public class NoSuchElementException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NoSuchElementException (String message) {
		super(message);
	}
	
	
	public NoSuchElementException (String message,Throwable cause) {
		super(message,cause);
	}

}
