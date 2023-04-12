package com.notesapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.notesapp.dao.NotesDao;
import com.notesapp.model.Note;

/**
 * Java servlet that retrieves all notes the signed in user has in the database
 * @author Alec Lehmphul
 */
@WebServlet("/FetchNotes")
public class FetchNotes extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		// Session object needed to obtain userId
		HttpSession session = request.getSession();
		int user = (int) session.getAttribute("userId");
		
		// Data Access Object used to interact with database
		NotesDao dao = new NotesDao();
		ArrayList<Note> notes = dao.getAllNotes(user);
		
		session.setAttribute("notes", notes);
		response.sendRedirect("my_notes.jsp");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); 
	}
	
	
}
