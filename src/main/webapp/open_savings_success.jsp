<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open Savings Success</title>
</head>
<body>
<h1 style="color:green">Savings Account Created!</h1>
<br>
<%
int newID = (Integer)request.getAttribute("newID");
out.println("<p>Your new savings account ID is " + newID + "</p>");
%>
<br>
<a href="MainMenu.jsp">Back to Main Menu</a>
</body>
</html>