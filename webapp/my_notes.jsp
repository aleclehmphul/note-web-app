<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.notesapp.model.Note"%>
<!DOCTYPE html>
<html>
	<head>	
		<meta charset="UTF-8">
		<meta content="device-width, initial-scale=1" name="viewport" />
		
		<title>My Notes</title>
		<link rel="stylesheet" type="text/css" href="./css/my_notes_styles.css">
		<link rel="stylesheet" type="text/css" href="./css/menu_navigation_styles.css">
		<script type="text/javascript" src="js/notes_page.js" defer></script>
	</head>
	<body>
		<header class="flex">
			<div>
				<!-- <button class="btn">My Notes</button> -->
				<button class="btn" id="profile-btn">My Profile</button>
				<form action="Logout" class="inline-block">
					<button type="submit" class="btn">Logout</button>
				</form>
			</div>
		</header>
		
		
		<!-- Section for creating new notes -->
		<section class="center">
			<button id="add-note-btn">Add New Note</button>
			
			<div class="hidden center" id="add-note-form">
			
				<form action="CreateNote" method="post">
					<input type="text" placeholder="New Note" name="header">
					<textarea ID="textarea" rows="" cols="" placeholder="Add Note Content Here" name="body"></textarea>
					<button type="submit">Save Note</button>
				</form>
				
			</div>
			
		</section>
		
		
		<!-- Main content containing all notes -->
		<main class="grid" id="main-content">

			<%
			
				// Prevents the user from accessing page via back button after logging out
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
						
				if (session.getAttribute("username") != null) {
					ArrayList<Note> notes = (ArrayList<Note>) session.getAttribute("notes");
					String noteHTML = "";
					String header = "";
					String body = "";
					String formattedBody = "";
					
					
					for (Note n : notes) {
						header = n.getHeader();
						body = n.getBody().replace("\n", "<br>");
						int id = n.getId();
						
						noteHTML = String.join("\n"
							,	"<div class=\"note\">"
							,		"<h3 class=\"note-title\">" + header + "</h3>"
							,		"<hr>"
							,		"<p class=\"note-content\">" + body + "</p>"
							,		"<form action=\"EditNote\" method=\"post\">"
							,			"<input type=\"hidden\" name=\"note\" value=\"" + id + "\">"
							,			"<button type=\"submit\">Edit</button>"
							,		"</form>"
							,		"<form onsubmit=\"return confirm('Are you sure you want to delete this note?')\" action=\"DeleteNote\" method=\"post\">"
							,			"<input type=\"hidden\" name=\"note\" value=\"" + id + "\">"
							,			"<button type=\"submit\">Delete</button>"
							,		"</form>"
							,	"</div>");
						
						out.write(noteHTML);
					}
					
					request.removeAttribute("notes");
				} else
					response.sendRedirect("login.jsp");   // if user is attempting to access without logging in, they are sent to login page
				
				
			
			%>

		</main>
	</body>
</html>