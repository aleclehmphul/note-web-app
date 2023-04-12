<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@page import="com.notesapp.model.Note"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Note Editor</title>
		<link rel="stylesheet" type="text/css" href="./css/menu_navigation_styles.css">
		<link rel="stylesheet" type="text/css" href="./css/edit_notes_styles.css">
		<script type="text/javascript" src="js/notes_page.js" defer></script>
	</head>
	<body>
		<header>
			<div>
				<!-- <button class="btn">My Notes</button> -->
				<form action="FetchNotes" method="post">
					<button class="btn" type="submit">My Notes</button>
				</form>
				<button class="btn" id="profile-btn">My Profile</button>
				<form action="Logout" class="inline-block">
					<button type="submit" class="btn">Logout</button>
				</form>
			</div>
		</header>
		
		
		<!-- Section for creating new notes -->
		<section class="center">
			<div class="center" id="edit-note-form">
				<form action="UpdateNote" method="post">
				
					<% 
						// Prevents the user from accessing page via back button after logging out
						response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
						
						// if user is attempting to access without logging in, they are sent to login page
						if (session.getAttribute("username") == null) {
							response.sendRedirect("login.jsp");
						} else {
							Note note = (Note) session.getAttribute("note");
							String html = "<input type=\"text\" placeholder=\"\" value=\"" + note.getHeader() + "\" name=\"header\">\n"
									+ "<textarea id=\"edit-body\" rows=\"\" cols=\"\" placeholder=\"\" name=\"body\">" + note.getBody() + "</textarea>\n";
							out.write(html);
						}
					%>
					
					<div>
						<button type="submit">Save Note</button>
						<button id="cancel-btn" type="button">Cancel</button>
					</div>
				</form>
			</div>
			
		</section>
	</body>
</html>