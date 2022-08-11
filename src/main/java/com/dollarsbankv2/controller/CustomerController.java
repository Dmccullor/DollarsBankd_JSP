package com.dollarsbankv2.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dollarsbankv2.model.Customer;
import com.dollarsbankv2.service.CustomerService;


public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static CustomerService service = CustomerService.getInstance();
       
   
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("account_id"));
		String pw = request.getParameter("pw");
		

			List<Customer> customerList = service.getAllCustomers();
			
			Optional<Customer> cust = customerList.stream()
					.filter((c) -> (c.getId() == id && c.getPassword().equals(pw)))
					.findFirst();
			
			if(cust.isEmpty()) {
				response.sendRedirect("invalid_credentials.jsp");
			}

			HttpSession session = request.getSession();
			session.setAttribute("principal", cust.get());
			
			response.sendRedirect("MainMenu.jsp");
			
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
