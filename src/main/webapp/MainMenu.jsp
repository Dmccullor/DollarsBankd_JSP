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
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Main Menu</h1>
				<br>
			</div>
		</div>
	
		<%
			Customer principal = (Customer)session.getAttribute("principal");
	
			out.println("Welcome " + principal.getName());
		%>
		<br><br>
		<a href="showinfo.jsp">Show Your Info</a>
		<br>
		<a href="transactions.jsp">View Recent Transactions</a>
		<br>
		<a href="deposit.jsp">Make a Deposit</a>
		<br>
		<a href="withdraw.jsp">Make a Withdrawal</a>
		<br>
		<a href="transfer.jsp">Transfer Funds</a>
		<br>
		<a href="open_savings.jsp">Open a Savings Account</a>
		<br><br>
		<a href="index.jsp">Logout</a>
	</div>
</body>
</html>