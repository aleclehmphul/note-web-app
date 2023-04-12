package com.notesapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.notesapp.dao.NotesDao;

/**
 * Java servlet that creates new notes for the signed in user
 * @author Alec Lehmphul
 */
@WebServlet("/CreateNote")
public class CreateNote extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		// Fetch the values the user submitted
		String header = request.getParameter("header");
		String body = request.getParameter("body");
		int user = (int) session.getAttribute("userId");
		
		// Data Access Object used to interact with database
		NotesDao dao = new NotesDao();
		dao.addNote(user, header, body);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/FetchNotes");
		rd.forward(request, response);
		
	}
	
}
