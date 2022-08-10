<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>Make a Deposit</h1>
<br>
<form action="DepositController" method="post">
	<div class="form-group">
		<label for="amount">How much do you want to deposit?</label>
		<input type="number" id="amount" name="amount" class="form-control" required>
		<input type="submit" value="Confirm" class="btn btn-primary">
		<span class="error"></span>
	</div>
</form>
<br>
<a href="MainMenu.jsp">Back</a>
</body>
</html>