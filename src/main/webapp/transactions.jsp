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
<h1>Recent Transactions</h1>
<%
Customer principal = (Customer)session.getAttribute("principal");
int user_id = principal.getId();
int counter = 1;
TransactionService transactionService = new TransactionService();


List<Transaction> transactionList = transactionService.findTransactionsByUserId(user_id);

Collections.reverse(transactionList);

Iterator<Transaction> i = transactionList.iterator();

while(counter <= 5 && i.hasNext()) {
	
	out.println(i.next());
	
	out.println("<br>");
	
	counter ++;
}

%>
<br>
<a href="MainMenu.jsp">Back</a>

</body>
</html>