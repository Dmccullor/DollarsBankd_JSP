<html>
<meta charset="UTF-8">
<title>Welcome to Dollars Bank</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<body>
<div class="container">
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Welcome to Dollars Bank</h1>
                <p class="lead">Please login below.</p>
            </div>
        </div>
        <!-- make sure to set both inputs with required attribute -->
        <form action="CustomerController" method="get">
            <div class="form-group">
                <label for="account_id">Account ID:</label> 
                <input type="number" id="account_id" name = "account_id" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label for="pw">Password</label> 
                <input type="password" id="pw" name = "pw" class="form-control" required>
            </div>
            
            <input type="submit" value="Login" class="btn btn-primary"> 
        </form>
    </div>
</body>
</html>
