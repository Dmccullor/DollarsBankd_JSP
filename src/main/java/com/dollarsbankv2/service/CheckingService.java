package com.dollarsbankv2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbankv2.model.Account;
import com.dollarsbankv2.exceptions.AccountNotFoundException;
import com.dollarsbankv2.model.Checking;

public class CheckingService {
	
	private static CheckingService single_instance = null;
	
	private CheckingService() {};
	
	public static CheckingService getInstance() {
		
		if(single_instance == null) {
			single_instance = new CheckingService();
		}
		
		return single_instance;
	}
	
	
	private static int idCounter = 1;
	private static List<Checking> checkingList = new ArrayList<Checking>();
	
	static {
		checkingList.add(new Checking(idCounter++, 10000, 1000, 1, 1));
		checkingList.add(new Checking(idCounter++, 5000, 1200, 2, 2));
		checkingList.add(new Checking(idCounter++, 1000, 500, 3, 0));
	}
	
	public List<Checking> getAllAccounts() {
		return checkingList;
	}
	
	public Checking findAccountById(int id) throws AccountNotFoundException {
		
		Optional<Checking> acct = checkingList.stream()
				.filter((a) -> a.getId() == id)
				.findFirst();
		
		if(acct.isEmpty()) {
			throw new AccountNotFoundException(id);
		}
		
		Checking acctObj = acct.get();
		
		return acctObj;
	}
	
	public <A extends Account> A createAccount(A acct) {
		acct.setId(idCounter++);
		 
		checkingList.add((Checking) acct);
		
		System.out.println("User ID: " + acct.getUser_id() + " has a new checking account with $" + ((Checking) acct).getInit_deposit());
		System.out.println("Your new checking account has an ID of: " + acct.getId());
		 
		return acct;
	}
	
	public boolean deleteAccount(int id) {
		
		for(Checking a: checkingList) {			
			if(a.getId() == id) {								
				checkingList.remove(a);
			}
		}
		
		return false;
	}
	
	public Checking getAccountByUserId(int id) throws AccountNotFoundException {
		
		Optional<Checking> acct = checkingList.stream()
				.filter((a) -> a.getUser_id() == id)
				.findFirst();
		
		if(acct.isEmpty()) {
			throw new AccountNotFoundException(id);
		}
		
		Checking acctObj = acct.get();
		
		return acctObj;
	}
	
	public <A extends Account> boolean updateAccount(A acct) {
		
		for(Checking a: checkingList) {
			
			if(a.getId() == acct.getId()) {
				
				checkingList.remove(a);
				
				checkingList.add((Checking) acct);
				
				System.out.println("Account Updated");
			}
		}
		
		return false;
	}

}
