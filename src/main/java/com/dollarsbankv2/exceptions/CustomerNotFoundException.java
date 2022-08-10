package com.dollarsbankv2.exceptions;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 6923745705441010018L;
	
	public CustomerNotFoundException(int id) {
		super("Customer with ID: " + id + " not found.");
	}

}
