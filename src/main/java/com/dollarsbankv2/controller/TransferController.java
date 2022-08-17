package com.dollarsbankv2.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dollarsbankv2.exceptions.AccountNotFoundException;
import com.dollarsbankv2.model.Customer;
import com.dollarsbankv2.model.Transaction;
import com.dollarsbankv2.model.Transaction.ToAcct;
import com.dollarsbankv2.model.Transaction.Type;
import com.dollarsbankv2.service.CheckingService;
import com.dollarsbankv2.service.SavingsService;
import com.dollarsbankv2.service.TransactionService;


public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TransactionService transactionService = TransactionService.getInstance();
	private CheckingService checkingService = CheckingService.getInstance();
	private SavingsService savingsService = SavingsService.getInstance();

    public TransferController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer principal = (Customer)session.getAttribute("principal");		
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		try {
			int checking_id = principal.getChecking_id();
			int user_id = principal.getId();
			Type type = Type.TRANSFER;
			ToAcct toAcct = ToAcct.CHECKING;
			int savings_id = principal.getSavings_id();
			double balance = (savingsService.getAccountByUserId(user_id)).getAmount();
			
			if(principal.getHas_savings() && request.getParameter("acct-type").equals("savings")) {
				toAcct = ToAcct.SAVINGS;
				balance = (checkingService.getAccountByUserId(user_id)).getAmount();
			}
			
			if(amount <= 0) {
				response.sendRedirect("amountzero.jsp");
			}
			else if (amount > balance) {
				response.sendRedirect("insufficient_funds.jsp");
			}
			else {
				Transaction tran = new Transaction(0, LocalDateTime.now(), type, toAcct, amount, user_id, checking_id, savings_id);
				
				transactionService.createTransaction(tran);
				request.setAttribute("amount", amount);
				RequestDispatcher rd = request.getRequestDispatcher("transfersuccess.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (AccountNotFoundException e) {
			e.getMessage();
		}
	}
}
