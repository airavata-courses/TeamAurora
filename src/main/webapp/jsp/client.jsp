
<!DOCTYPE HTML>
<html>
<title>Input Details</title>
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
		<link href="navbar.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
	</head>
	
	<body>
		<div class="container">
			<nav class="navbar navbar-light bg-faded">
				<div id="navbar-header">  
					<ul class="nav navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/api/jobs.jsp">Existing Jobs</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="#">Create New Job<span class="sr-only">(current)</span></a>
						</li>
					</ul>
				</div>
			</nav> <!-- /navbar -->
		
		<div class="jumbotron">
		<form method="post" action="${pageContext.request.contextPath}/api/urldata">
			<div id="datetimepicker1" class="input-append date">
				<label for="datetimepicker1">Date</label>
				<span class="add-on">
					<input name="datepicker" id = "date" type="text" style="width:20.5%" value="Choose Date" class="form-control"></input>
					<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
				</span>
			</div>  
			<div id="datetimepicker" class="input-append date">
				<label for="datetimepicker">Time</label>
				<span class="add-on">
					<input name="timepicker" type="text" style="width:20.5%" value="Choose Time" class="form-control"></input>
					<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
				</span>
			</div>
			<div id="nexrad_station"> 
				<label for="nexrad_station">NEXRAD Station</label>
				<select style="width:20.5%" name="nexrad_station" class="form-control">
					<option value="">Select</option>
					<option value="KABR">KABR</option>
					<option value="KABX">KABX</option>
					<option value="KAKQ">KAKQ</option>
					<option value="KAMA">KAMA</option>
					<option value="KAMX">KAMX</option>
					<option value="KAPX">KAPX</option>
					<option value="KIND">KIND</option>
				</select>
			</div>
			
			<div id="reqId" class="">
				<span class="add-on">
					<input name="requestId" value = "" id = "requestId" type="text" style="display:none; width:20.5%" value="" class="form-control"></input>	
				</span>
			</div>
					
			<br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>


</script>
	<script type="text/javascript"
     	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script> 
    <script type="text/javascript"
     	src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
     	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
     	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>
    <script type="text/javascript">
      $('#datetimepicker').datetimepicker({
        format: 'hh:mm:ss',
		pickDate: false,
		pick12HourFormat: true
      });
    </script>
    
    <script type="text/javascript">
    $( document ).ready(function() {
    	 $('#requestId').val(getUrlVars()["requestId"])
    	 console.log($('#requestId').val(getUrlVars()["requestId"]))
    });
    
    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }
    
    
    </script>
    
    
    <script type="text/javascript">
      $('#datetimepicker1').datetimepicker({
        format: 'yyyy/MM/dd',
		pickTime: false,
      });
    </script>
	<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
		
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	
  </body>
<html>
<!-- References Used: https://tarruda.github.io/bootstrap-datetimepicker/-->
