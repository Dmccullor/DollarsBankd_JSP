package com.dollarsbankv2.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dollarsbankv2.model.Transaction;
import com.dollarsbankv2.exceptions.AccountNotFoundException;
import com.dollarsbankv2.model.Customer;
import com.dollarsbankv2.model.Transaction.ToAcct;
import com.dollarsbankv2.model.Transaction.Type;
import com.dollarsbankv2.service.TransactionService;


public class DepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TransactionService transactionService = new TransactionService();
       

    public DepositController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer principal = (Customer)session.getAttribute("principal");		
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		int checking_id = principal.getChecking_id();
		int user_id = principal.getId();
		Type type = Type.DEPOSIT;
		ToAcct toAcct = ToAcct.CHECKING;
		int savings_id = principal.getSavings_id();
		
		if(amount <= 0) {
			request.setAttribute("error", "Amount must be greater than 0");
			response.sendRedirect("deposit.jsp");
		}
		else {
			Transaction tran = new Transaction(0, LocalDateTime.now(), type, toAcct, amount, user_id, checking_id, savings_id);
			try {
				transactionService.createTransaction(tran);
				request.setAttribute("amount", amount);
				RequestDispatcher rd = request.getRequestDispatcher("depositsuccess.jsp");
				rd.forward(request, response);
				
			} catch (AccountNotFoundException e) {
				e.getMessage();
			} catch(ConcurrentModificationException e) {
				
			}
		}
		
	}

}
