<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Withdrawal</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Make a Deposit</h1>
			</div>
		</div>
		<br>
		<form action="WithdrawController" method="post">
			<%
			Customer principal = (Customer)session.getAttribute("principal");
			if(principal.getHas_savings()) {
				out.println("<div class='form-group'>");
				out.println("<label for='acct-type'>To which account would you like to withdraw?</label>");
				out.println("<br>");
				out.println("<input type='radio' id='checking' name='acct-type' value='checking' checked>");
				out.println("<label for='checking'>Checking</label>");
				out.println("<input type='radio' id='savings' name='acct-type' value='savings'>");
				out.println("<label for='savings'>Savings</label>");
				out.println("</div>");
			}
			%>
			
			<div class="form-group">
				<label for="amount">How much do you want to withdraw?</label>
				<input type="number" id="amount" name="amount" class="form-control" required>
			</div>
			<input type="submit" value="Confirm" class="btn btn-primary">
		</form>
		<br>
		<a href="MainMenu.jsp">Back</a>
	</div>
</body>
</html>