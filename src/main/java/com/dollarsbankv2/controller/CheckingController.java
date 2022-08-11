package com.dollarsbankv2.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dollarsbankv2.model.Transaction;
import com.dollarsbankv2.exceptions.AccountNotFoundException;
import com.dollarsbankv2.model.Checking;
import com.dollarsbankv2.model.Customer;
import com.dollarsbankv2.model.Transaction.ToAcct;
import com.dollarsbankv2.model.Transaction.Type;
import com.dollarsbankv2.service.CheckingService;
import com.dollarsbankv2.service.CustomerService;
import com.dollarsbankv2.service.TransactionService;


public class CheckingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static CheckingService checkingService = CheckingService.getInstance();
	private static CustomerService customerService = CustomerService.getInstance();
	private static TransactionService transactionService = TransactionService.getInstance();
       
    public CheckingController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer principal = (Customer)session.getAttribute("principal");
		double init_amount = Double.parseDouble(request.getParameter("init_amount"));
		int user_id = principal.getId();
		
		Checking newChecking = new Checking(0, 0, init_amount, user_id, 0);
		Checking newAcct = checkingService.createAccount(newChecking);
		principal.setChecking_id(newAcct.getId());
		customerService.updateCustomer(principal);
		
		int checking_id = principal.getChecking_id();
		Type type = Type.DEPOSIT;
		ToAcct toAcct = ToAcct.CHECKING;
		int savings_id = 0;
		
		Transaction tran = new Transaction(0, LocalDateTime.now(), type, toAcct, init_amount, user_id, checking_id, savings_id);
		try {
			transactionService.createTransaction(tran);
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		response.sendRedirect("MainMenu.jsp");
	}

}
