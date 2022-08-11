<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Create an Account</h1>
			<p class="lead">Please fill in the form below.</p>
		</div>
	</div>
	<form action="CustomerController" method="post">
		<div class="form-group">
			<label for="name">Full name:</label>
			<input type="text" id="name" name="name" class="form-control" required>
		</div>
		
		<div class="form-group">
			<label for="address">Address:</label>
			<input type="text" id="address" name="address" class="form-control" required>
		</div>
		
		<div class="form-group">
			<label for="phone">Phone Number:</label>
			<input type="number" id="phone" name="phone" class="form-control" required>
		</div>
		
		<div class="form-group">
			<label for="pw">Password:</label>
			<input type="password" id="pw" name="pw" class="form-control" required>
		</div>
		
		<input type="submit" value="Submit" class="btn btn-primary">
	</form>
	<br>
	<a href="index.jsp">Cancel</a>
</div>
</body>
</html>