<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit Success</title>
</head>
<body>
<h1 style="color:green">Deposit Success!</h1>
<br>
<%
double amount = (Double)request.getAttribute("amount");
out.println("<p>$" + amount + " has been added to your checking account.</p>");
%>
<br>
<a href="MainMenu.jsp">Back</a>
</body>
</html>