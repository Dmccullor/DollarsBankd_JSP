package com.dollarsbankv2.exceptions;

public class TransactionNotFoundException extends Exception {

	private static final long serialVersionUID = -8679156339909497541L;
	
	public TransactionNotFoundException(int id) {
		super("Transaction ID: " + id + " not found.");
	}

}
