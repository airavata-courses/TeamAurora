<html>

	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<div class="container"> 
			<div class ="row">		
				<div class="col-md-4 col-md-offset-4">
					<form action="${pageContext.request.contextPath}/api/login" method="post">  
						<label for="username">Enter User Name:</label>
						<input type="text" class="form-control" id="username" name="username"/><br/><br/>  
						<label for="password">Enter Password:</label>
						<input type="password" class="form-control" id="password" name="password"/><br/><br/>  
						<input type="submit" class="btn btn-primary" value="Login"/>  
					</form>
				</div>
			</div>
		</div>
	</body>

</html>