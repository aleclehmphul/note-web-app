package com.notesapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class BaseDao {
	// Returns connection to PostgreSQL database
	public Connection establishConnection() {
		try {
			Dotenv dotenv = Dotenv.load();
			
			// Connection to database
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASS"));
			return con;
			
		} catch (Exception e) {
			return null;
		}
	}
}
