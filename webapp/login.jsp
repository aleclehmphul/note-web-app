<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		
		<link rel="stylesheet" type="text/css" href="./css/login_registration_styles.css">
	</head>
	<body>
		<!-- Login Form -->
		<form action="Login" method="post" id="login-form">
			<h2>Welcome!<br>Please Login Below</h2>
			<hr>
			<label for="username">Username</label>
			<br>
			<input type="text" name="username" id="username">
			<br>
			<label for="pswd">Password</label>
			<br>
			<input type="password" name="pswd" id="pswd">
			<br>
			<input type="submit" value="Login" id="login-submit-btn">
			<p class="error"><% if (request.getAttribute("errorMessage") != null) out.println(request.getAttribute("errorMessage"));%></p>
			
			<a href="register.html">Need to make an account? Register here!</a>
		</form>
		
	</body>
</html>