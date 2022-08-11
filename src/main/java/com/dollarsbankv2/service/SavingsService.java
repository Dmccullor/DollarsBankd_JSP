package com.dollarsbankv2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbankv2.model.Account;
import com.dollarsbankv2.exceptions.AccountNotFoundException;
import com.dollarsbankv2.model.Savings;

public class SavingsService {
	
	private static SavingsService single_instance = null;
	
	private SavingsService() {};
	
	public static SavingsService getInstance() {
		
		if(single_instance == null) {
			single_instance = new SavingsService();
		}
		
		return single_instance;
	}
	
	private static int idCounter = 1;
	private static List<Savings> savingsList = new ArrayList<Savings>();
	
	static {
		savingsList.add(new Savings(idCounter++, 10000, 1, 1));
		savingsList.add(new Savings(idCounter++, 15000, 2, 2));
	}
	
	public List<Savings> getAllAccounts() {
		return savingsList;
	}
	
	public Savings findAccountById(int id) throws AccountNotFoundException {
		
		Optional<Savings> acct = savingsList.stream()
				.filter((a) -> a.getId() == id)
				.findFirst();
		
		if(acct.isEmpty()) {
			throw new AccountNotFoundException(id);
		}
		
		Savings acctObj = acct.get();
		
		return acctObj;
	}
	
	public <A extends Account> A createAccount(A acct) {
		acct.setId(idCounter++);
		
		savingsList.add((Savings) acct);
		
		return acct;
	}
	
	public boolean deleteAccount(int id) {
		
		for(Savings a: savingsList) {
			
			if(a.getId() == id) {
				
				savingsList.remove(a);
			}
		}
		
		return false;
	}
	
	public Savings getAccountByUserId(int id) throws AccountNotFoundException {
		
		Optional<Savings> acct = savingsList.stream()
				.filter((a) -> a.getUser_id() == id)
				.findFirst();
		
		if(acct.isEmpty()) {
			throw new AccountNotFoundException(id);
		}
		
		Savings acctObj = acct.get();
		
		return acctObj;
	}
	
	public Savings getAccountByCheckingId(int id) throws AccountNotFoundException {
		
		Optional<Savings> acct = savingsList.stream()
				.filter((a) -> a.getChecking_id() == id)
				.findFirst();
		
		if(acct.isEmpty()) {
			throw new AccountNotFoundException(id);
		}
		
		Savings acctObj = acct.get();
		
		return acctObj;
	}
	
	public <A extends Account> boolean updateAccount(A acct) {
		// TODO Auto-generated method stub
			for(Savings a: savingsList) {
			
			if(a.getId() == acct.getId()) {
				
				savingsList.remove(a);
				
				savingsList.add((Savings) acct);
			}
		}
		return false;
	}

}
