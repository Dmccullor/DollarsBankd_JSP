package com.dollarsbankv2.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbankv2.exceptions.AccountNotFoundException;
import com.dollarsbankv2.model.Checking;
import com.dollarsbankv2.model.Savings;
import com.dollarsbankv2.exceptions.TransactionNotFoundException;
import com.dollarsbankv2.model.Transaction;
import com.dollarsbankv2.model.Transaction.ToAcct;
import com.dollarsbankv2.model.Transaction.Type;



public class TransactionService {
	
	private static int idCounter = 1;
	private static List<Transaction> transactionList = new ArrayList<Transaction>();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	private static CheckingService checkingService = new CheckingService();
	private static SavingsService savingsService = new SavingsService();
	
	static {
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.CHECKING, 1000.00, 1, 1, 0));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.CHECKING, 9000.00, 1, 1, 0));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.SAVINGS, 10000.00, 1, 1, 1));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.CHECKING, 1200.00, 2, 2, 0));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.CHECKING, 3800.00, 2, 2, 2));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.SAVINGS, 15000.00, 2, 2, 2));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.CHECKING, 500.00, 3, 3, 0));
		transactionList.add(new Transaction(idCounter++, LocalDateTime.now(), Type.DEPOSIT, ToAcct.CHECKING, 500.00, 3, 3, 0));
	}
	
	public List<Transaction> getAllTransactions() {
		return transactionList;
	}
	
	public Transaction findTransactionById(int id) throws TransactionNotFoundException {
		
		Optional<Transaction> tran = transactionList.stream()
				.filter((t) -> t.getId() == id)
				.findFirst();
		
		if(tran.isEmpty()) {
			throw new TransactionNotFoundException(id);
		}
		
		Transaction tranObj = tran.get();
		
		return tranObj;
	}
	
	public boolean createTransaction(Transaction tran) throws AccountNotFoundException {
		
		tran.setId(idCounter++);
		
		transactionList.add(tran);
		
		if(tran.getType() == Type.DEPOSIT && tran.getToAcct() == ToAcct.CHECKING) {
			
			Checking currentAccount = (Checking) checkingService.getAccountByUserId(tran.getUser_id());
			
			currentAccount.setAmount(currentAccount.getAmount() + tran.getAmount());
			
			checkingService.updateAccount(currentAccount);
		}
		
		else if(tran.getType() == Type.DEPOSIT && tran.getToAcct() == ToAcct.SAVINGS) {
			
			Savings currentAccount = (Savings) savingsService.getAccountByUserId(tran.getUser_id());
			
			currentAccount.setAmount(currentAccount.getAmount() + tran.getAmount());
			
			savingsService.updateAccount(currentAccount);
		}
		
		else if(tran.getType() == Type.WITHDRAWAL && tran.getToAcct() == ToAcct.CHECKING) {
			
			Checking currentAccount = (Checking) checkingService.getAccountByUserId(tran.getUser_id());
			
			currentAccount.setAmount(currentAccount.getAmount() - tran.getAmount());
			
			checkingService.updateAccount(currentAccount);
		}
		
		else if(tran.getType() == Type.WITHDRAWAL && tran.getToAcct() == ToAcct.SAVINGS) {
			
			Savings currentAccount = (Savings) savingsService.getAccountByUserId(tran.getUser_id());
			
			currentAccount.setAmount(currentAccount.getAmount() - tran.getAmount());
			
			savingsService.updateAccount(currentAccount);
		}
		
		else if(tran.getType() == Type.TRANSFER && tran.getToAcct() == ToAcct.CHECKING) {
			
			Checking currentCheckingAccount = (Checking) checkingService.getAccountByUserId(tran.getUser_id());
			
			Savings currentSavingsAccount = (Savings) savingsService.getAccountByUserId(tran.getUser_id());
			
			currentCheckingAccount.setAmount(currentCheckingAccount.getAmount() + tran.getAmount());
			
			currentSavingsAccount.setAmount(currentSavingsAccount.getAmount() - tran.getAmount());
			
			checkingService.updateAccount(currentCheckingAccount);
			
			savingsService.updateAccount(currentSavingsAccount);
		}
		
		else {
			
			Checking currentCheckingAccount = (Checking) checkingService.getAccountByUserId(tran.getUser_id());
			
			Savings currentSavingsAccount = (Savings) savingsService.getAccountByUserId(tran.getUser_id());
			
			currentCheckingAccount.setAmount(currentCheckingAccount.getAmount() - tran.getAmount());
			
			currentSavingsAccount.setAmount(currentSavingsAccount.getAmount() + tran.getAmount());
			
			checkingService.updateAccount(currentCheckingAccount);
			
			savingsService.updateAccount(currentSavingsAccount);
		}
		
		return false;
	}
	
	public List<Transaction> findTransactionsByUserId(int id) {
		
		List<Transaction> userTransactionList = new ArrayList<Transaction>();
		
		for(Transaction t: transactionList) {
			if(t.getUser_id() == id) {
				userTransactionList.add(t);
			}
		}
		return userTransactionList;
	}

}
