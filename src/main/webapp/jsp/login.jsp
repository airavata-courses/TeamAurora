<html>
	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<meta name="google-signin-client_id" content="758193546502-npj4ptehrnumf6h6s04vfim48i5j2j7p.apps.googleusercontent.com">
<script src="jquery.min.js"></script>
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

               /* var profileHTML = '<div class="profile"><div class="head">Welcome '+resp.name.givenName+'! <a href="javascript:void(0);" onclick="signOut();">Sign out</a></div>';
                profileHTML += '<img src="'+resp.image.url+'"/><div class="proDetails"><p>'+resp.displayName+'</p><p>'+resp.emails[0].value+'</p><p>'+resp.gender+'</p><p>'+resp.id+'</p><p><a href="'+resp.url+'">View Google+ Profile</a></p></div></div>';
                $('.userContent').html(profileHTML);
                $('#gSignIn').slideUp('slow');*/
         
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
    	window.location = "http://localhost:8080/apigateway/jsp/login.jsp";
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
			<div class ="row">		
				<div class="col-md-4 col-md-offset-4" style="padding-top: 50; ">
					<form action="${pageContext.request.contextPath}/api/login" method="post">  
						<label for="username" style="height:30;">Enter User Name:</label>
						<input type="text" style="height:50;" class="form-control" id="username" name="username"/><br/>
						<label for="password" style="height:30;">Enter Password:</label>
						<input type="password" style="height:50;" class="form-control" id="password" name="password"/><br/><br/>
						<input type="submit" class="abcRioButton abcRioButtonLightBlue" style="background:#4B8EFA;color:white; border:#4B8EFA; font-size:16; border: none; width: 220; height: 50;" value="Sign In"/>
						<br/><br/> 
						<!-- <input type="button" class="btn btn-primary" id = "gSignIn" value="SignIn With Google"/> -->  
						<div id="gSignIn"></div>
						 <!-- HTML for displaying user details
						<div class="userContent"/>-->
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
