package com.dollarsbankv2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dollarsbankv2.model.Customer;
import com.dollarsbankv2.model.Savings;
import com.dollarsbankv2.service.CustomerService;
import com.dollarsbankv2.service.SavingsService;

public class OpenSavingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SavingsService savingsService = SavingsService.getInstance();
	private CustomerService customerService = CustomerService.getInstance();
	
    public OpenSavingsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer principal = (Customer)session.getAttribute("principal");
		String confirmation = request.getParameter("confirmation");
		
		if(confirmation.equals("yes")) {
			Savings savingsAcct = new Savings(0, 0, principal.getId(), principal.getChecking_id());
			Savings newAcct = savingsService.createAccount(savingsAcct);
			principal.setSavings_id(newAcct.getId());
			principal.setHas_savings(true);
			customerService.updateCustomer(principal);
			
			request.setAttribute("newID", newAcct.getId());
			RequestDispatcher rd = request.getRequestDispatcher("open_savings_success.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("open_savings_denied.jsp");
			rd.forward(request, response);
		}
	}

}
