package com.notesapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class LoginDao extends BaseDao {

	public boolean checkCredentials(String userEnteredUsername, String userEnteredPassword) {
		
		// Query to check if username and password exists in database
		String sql = "SELECT * FROM users WHERE username=? AND password=?";

		try (Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setString(1, userEnteredUsername);
			statement.setString(2, userEnteredPassword);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
