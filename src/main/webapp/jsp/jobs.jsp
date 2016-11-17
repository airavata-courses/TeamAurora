<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="../../favicon.ico">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://apis.google.com/js/client:platform.js?onload=renderButton" async defer></script>
	
		<title> Weather Forecast Jobs </title>

		<!-- Custom styles for this template -->
		<link href="navbar.css" rel="stylesheet">
	</head>

	<body>
		<div class="container">
			<nav class="navbar navbar-light bg-faded">
				<div id="navbar-header">  
					<ul class="nav navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="#">Existing Jobs<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="client.jsp">Create New Job</a>
						</li>
					</ul>
				</div>
			</nav> <!-- /navbar -->

			<!-- Main component for a primary marketing message or call to action -->
			<div class="jumbotron">
				<table width="60%" align="center" class = "table">
					<tr bgcolor="#337ab7" align="center">
						<td><b>Request Id</b></td>
						<td><b>Service Name</b></td>
						<td><b>Status</b></td>
						<td><b>Start Time</b></td>
					</tr>
					<c:forEach items="${list}" var="current">
					<!--  <tr bgcolor="#DEB887">-->
					<tr align="center">
						<td>${current.requestId} </td>
						<td>${current.serviceName} </td>
						<td>${current.status} </td>
						<td>${current.startTime} </td>
					</tr>
					</c:forEach>
				</table>

			</div>
		</div> <!-- /container -->


		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
		
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	</body>
</html>
