<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Success</title>
</head>
<body>
<h1 style="color:green">Transfer Successful!</h1>
<br>
<%
double amount = (Double)request.getAttribute("amount");
out.println("<p>$" + amount + " has been transferred between accounts.</p>");
%>
<br>
<a href="MainMenu.jsp">Back to Main Menu</a>
</body>
</html>