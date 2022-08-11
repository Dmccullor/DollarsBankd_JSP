<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dollarsbankv2.model.Transaction" %>
<%@ page import="com.dollarsbankv2.service.TransactionService" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recent Transactions</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Recent Transactions</h1>
			</div>
		</div>
		<%
		Customer principal = (Customer)session.getAttribute("principal");
		int user_id = principal.getId();
		int i = 0;
		TransactionService transactionService = TransactionService.getInstance();
		
		List<Transaction> transactionList = transactionService.findTransactionsByUserId(user_id);
		
		Collections.reverse(transactionList);
		
		while(transactionList.size() >= (i + 1)) {
			
			if(i <= 4) {
			out.println(transactionList.get(i));
			
			out.println("<br>");
			
			i++;
			} else {
				i++;
			}
		}
		
		%>
		<br>
		<a href="MainMenu.jsp">Back</a>
	</div>
</body>
</html>