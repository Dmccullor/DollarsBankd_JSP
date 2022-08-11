<%@ page import="com.dollarsbankv2.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Funds</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Transfer Funds</h1>
			</div>
		</div>
		<br>
		
			<%
			Customer principal = (Customer)session.getAttribute("principal");
			
			if(principal == null) {
				response.sendRedirect("index.jsp");
			}
			else if(principal.getHas_savings()) {
				out.println("<form action='TransferController' method='post'>");
				out.println("<div class='form-group'>");
				out.println("<label for='acct-type'>To which account would you like to transfer?</label>");
				out.println("<br>");
				out.println("<input type='radio' id='checking' name='acct-type' value='checking' checked>");
				out.println("<label for='checking'>Checking</label>");
				out.println("<input type='radio' id='savings' name='acct-type' value='savings'>");
				out.println("<label for='savings'>Savings</label>");
				out.println("</div>");

			
				out.println("<div class='form-group'>");
				out.println("<label for='amount'>How much do you want to transfer?</label>");
				out.println("<input type='number' id='amount' name='amount' class='form-control' required>");
				out.println("</div>");
				out.println("<input type='submit' value='Confirm' class='btn btn-primary'>");
				out.println("</form>");
			}
			else {
				out.println("<p style='color:red'>You must have a savings account in order to transfer funds.</p>");
			}
			%>
		<br>
		<a href="MainMenu.jsp">Back</a>
	</div>
</body>
</html>