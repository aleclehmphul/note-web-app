package com.notesapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.notesapp.dao.NotesDao;

/**
 * Java servlet that deletes an existing note for the signed in user
 * @author Alec Lehmphul
 */
@WebServlet("/DeleteNote")
public class DeleteNote extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("note"));
		NotesDao dao = new NotesDao();
		dao.deleteNote(id);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/FetchNotes");
		rd.forward(request, response);
	}
	
}
