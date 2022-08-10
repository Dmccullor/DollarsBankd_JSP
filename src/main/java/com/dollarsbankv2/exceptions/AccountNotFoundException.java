package com.dollarsbankv2.exceptions;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 9067884462589292077L;
	
	public AccountNotFoundException(int id) {
		super("Account with an ID of " + id + " not found.");
	}

}
