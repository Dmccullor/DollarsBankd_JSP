package com.dollarsbankv2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dollarsbankv2.exceptions.CustomerNotFoundException;
import com.dollarsbankv2.model.Customer;
import com.dollarsbankv2.service.CustomerService;


public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static CustomerService service = new CustomerService();
       
   
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("account_id"));
		
		try {
			Customer customer = service.getCustomerById(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("principal", customer);
			
			response.sendRedirect("MainMenu.jsp");
			
//			request.setAttribute("principal", customer);
//			RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
//			rd.forward(request, response);
			
		} catch (CustomerNotFoundException e) {
			e.getMessage();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
