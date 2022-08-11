<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open Savings</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Open a Savings Account</h1>
			</div>
		</div>
		<br>
		<%
		Customer principal = (Customer)session.getAttribute("principal");
		if(principal.getHas_savings()) {
			out.println("<p style='color:red'>You already have a savings account.");
		}
		else {
			out.println("<form action='OpenSavingsController' method='post'>");
			out.println("<div class='form-group'>");
			out.println("<label for='confirmation'>Are you sure you want to open a savings account?</label>");
			out.println("<br>");
			out.println("<input type='radio' id='yes' name='confirmation' value='yes' checked>");
			out.println("<label for='yes'>Yes</label>");
			out.println("<input type='radio' id='no' name='confirmation' value='no'>");
			out.println("<label for='no'>No</label>");
			out.println("</div>");
			out.println("<br>");
			out.println("<input type='submit' value='Confirm' class='btn btn-primary'>");
			out.println("</form>");
		}
		%>
		<br>
		<a href="MainMenu.jsp">Back</a>
	</div>
</body>
</html>