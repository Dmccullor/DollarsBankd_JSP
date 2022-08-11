<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Checking</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Account Created!</h1>
			<%
			Customer principal = (Customer)session.getAttribute("principal");
			int id = principal.getId();
			
			out.println("<p class='lead'>Your new Account ID is " + id + ". Use this ID # to log in.");
			%>
		</div>
	</div>
	<form action="CheckingController" method="post">
		<div class="form-group">
			<label for="init_amount">New Accounts require an initial Deposit of at least $500. How much would you like to deposit?</label>
			<input type="number" id="init_amount" name="init_amount" min="500" class="form-control" required>
		</div>
		<input type="submit" value="Submit" class="btn btn-primary">
	</form>
</div>
</body>
</html>