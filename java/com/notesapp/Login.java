package com.notesapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.notesapp.dao.LoginDao;
import com.notesapp.dao.UserDao;

/**
 * Java servlet that authenticates the user upon log in.
 * @author Alec Lehmphul
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("pswd");
		
		LoginDao loginDao = new LoginDao();
		UserDao userDao = new UserDao();
		
		// Checks if user is in database
		if (loginDao.checkCredentials(username, password)) {
			// Add user info to session object
			HttpSession session = request.getSession();
			session.setAttribute("userId", userDao.getUserId(username));
			session.setAttribute("username", username);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/FetchNotes");
			rd.forward(request, response);
			
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("errorMessage", "Invalid username or password");
			rd.forward(request, response);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
