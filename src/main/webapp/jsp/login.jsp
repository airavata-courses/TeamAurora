<html>
  <head>
    <meta charset="utf-8">
    <title>Login</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<!-- Custom styles for this template -->
    <style>
    	body {
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #eee;
		}
		
		.form-signin {
		  max-width: 330px;
		  padding: 15px;
		  margin: 0 auto;
		}
		.form-signin .form-signin-heading,
		.form-signin .checkbox {
		  margin-bottom: 10px;
		}
		.form-signin .checkbox {
		  font-weight: normal;
		}
		.form-signin .form-control {
		  position: relative;
		  height: auto;
		  -webkit-box-sizing: border-box;
		          box-sizing: border-box;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.form-signin input[type="email"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
		    	
    </style>
		
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<meta name="google-signin-client_id" content="758193546502-npj4ptehrnumf6h6s04vfim48i5j2j7p.apps.googleusercontent.com">
	<script src="https://apis.google.com/js/client:platform.js?onload=renderButton" async defer></script>
	
	<script>
	    function onSuccess(googleUser) {
	    	var profile = googleUser.getBasicProfile();
	        gapi.client.load('plus', 'v1', function () {
            var request = gapi.client.plus.people.get({
                'userId': 'me'
            });
			var email = "";
            //Display the user details
            request.execute(function (resp) {
           	email = resp.emails[0].value;

            var url = "${pageContext.request.contextPath}/api/login";
            var params = "username="+email+"&google=true";
            var xhr = new XMLHttpRequest();
            xhr.open("POST", url, true);
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                   // Action to be performed when the document is read;
                	window.location.replace("${pageContext.request.contextPath}/jsp/client.jsp");
                }
            };
            //Send the proper header information along with the request
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send(params);
            });
	        });
	        
	    }
	    function onFailure(error) {
	    	window.location = "${pageContext.request.contextPath}/jsp/login.jsp";
	        alert("Unable to login");
	    }
	 
	    function renderButton() {
	        gapi.signin2.render('gSignIn', {
	            'scope': 'profile email',
	            'width': 220,
	            'height': 50,
	            'longtitle': true,
	            'theme': 'dark',
	            'onsuccess': onSuccess,
	            'onfailure': onFailure
	        });
	    }
	
		/*
	    function signOut() {
	        var auth2 = gapi.auth2.getAuthInstance();
	        auth2.signOut().then(function () {
	            $('.userContent').html('');
	            $('#gSignIn').slideDown('slow');
	        });
	    }*/
	    
	</script>

  </head>

  <body>

    <div class="container">

      <form action="${pageContext.request.contextPath}/api/login" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="username" class="sr-only">Email username</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <div id="gSignIn"></div>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
