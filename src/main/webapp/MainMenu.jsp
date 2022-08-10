<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Menu</title>
</head>
<body>
<h1>Main Menu</h1>
<%
	Customer principal = (Customer)session.getAttribute("principal");

	out.println("Welcome " + principal.getName());
%>
<br>
<a href="showinfo.jsp">Show Your Info</a>
<br>
<a href="transactions.jsp">View Recent Transactions</a>
<br>
<a href="deposit.jsp">Make a Deposit</a>
<br>
<a href="index.jsp">Back</a>
</body>
</html>