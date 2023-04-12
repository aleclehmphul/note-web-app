<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
	</head>
	
	<body>
		
		<%
		
			// Prevents the user from accessing page via back button after logging out
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
					
			if (session.getAttribute("username") != null) {
				
				// Need to create a profile servlet that access db and stores profile info in session
				
				
				
			} else
				response.sendRedirect("login.jsp");   // if user is attempting to access without logging in, they are sent to login page
		

		%>
		
		Name :  Kate Moss 				      (Change)<br>
		Email :   example321@example.email    (Change) <br>
		Username :  kmoss					  (Change) <br>
		Password :  *****				      (Change) <br>
		
	</body>
	
</html>