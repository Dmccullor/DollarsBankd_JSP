<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page import="com.dollarsbankv2.service.CheckingService" %>
<%@ page import="com.dollarsbankv2.service.SavingsService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Information</title>
</head>
<body>
<h1>Customer Profile</h1>
<%
	Customer principal = (Customer)session.getAttribute("principal");
	
	out.println("<b>Customer ID: </b>" + principal.getId() + "<br>");
	out.println("<b>Name: </b>" + principal.getName() + "<br>");
	out.println("<b>Address: </b>" + principal.getAddress() + "<br>");
	out.println("<b>Phone #: </b>" + principal.getPhone() + "<br>");
	out.println("<b>Checking Account #: </b>" + principal.getChecking_id() + "<br>");
	
	if(principal.getHas_savings() == true) {
		out.println("<b>Savings Account #: </b>" + principal.getSavings_id() + "<br>");
	}
	
%>
<br>
<h2>Balance</h2>

<%
	CheckingService checkingService = new CheckingService();
	SavingsService savingsService = new SavingsService();
	
	double checkingBalance = checkingService.getAccountByUserId(principal.getId()).getAmount();
	out.println("<b>Checking:</b> $" + checkingBalance + "<br>");
	
	if(principal.getHas_savings() == true) {
		double savingsBalance = savingsService.getAccountByUserId(principal.getId()).getAmount();
		out.println("<b>Savings:</b> $" + savingsBalance);
	}
%>
<br>
<a href="MainMenu.jsp">Back</a>
</body>
</html>