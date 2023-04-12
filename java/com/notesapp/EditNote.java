package com.notesapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.notesapp.dao.NotesDao;
import com.notesapp.model.Note;

/**
 * Java servlet that handles note editing functionality
 * @author Alec Lehmphul
 */
@WebServlet("/EditNote")
public class EditNote extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("note"));
		HttpSession session = request.getSession();
		session.setAttribute("noteId", id);
		
		NotesDao dao = new NotesDao();
		Note note = dao.getNote(id);
		
		session.setAttribute("note", note);
		
		response.sendRedirect("edit_note.jsp");
	}
}
