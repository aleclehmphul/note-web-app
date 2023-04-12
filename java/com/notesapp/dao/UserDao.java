package com.notesapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.notesapp.model.User;

import io.github.cdimascio.dotenv.Dotenv;

public class UserDao extends BaseDao {
	
	// Returns a single user using user id
	public User getUser(int userId) {
		User user = null;
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "SELECT * FROM users WHERE user_id=" + userId;
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next())
				user =  new User(userId, 
							result.getString("first_name"), 
							result.getString("last_name"),
							result.getString("email"),
							result.getString("username"));
			
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	// Returns a list of all users
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();;
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
		
			String sql = "SELECT * FROM users";
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next())
				users.add(new User(result.getInt("user_id"), 
							result.getString("firstName"), 
							result.getString("last_name"), 
							result.getString("email"), 
							result.getString("username")));
			
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (users.size() > 0)
			return users;
		
		return null;
	}
	
	
	public int getUserId(String username) {
		int userId = 0;
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			
			String sql = "SELECT * FROM users WHERE username='" + username + "'";
			ResultSet result = statement.executeQuery(sql);
			result.next();
			userId = result.getInt("user_id");
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	
	public void addUser(String firstName, String lastName, String email, String username, String password) {
		String sql = "INSERT INTO users "
				+ "(first_name, last_name, email, username, password) VALUES (?,?,?,?,?)";
		try (Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(sql)
		) {
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, username);
			statement.setString(5, password);
			
			int rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteUser(int userId) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			
			String sql = "DELETE FROM users WHERE user_id=" + userId;
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//===========================================================
	// Update database when user changes their information
	
	
	public void updateUser(User user) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "UPDATE users SET"
					+ "first_name='" + user.getFirstName() + "',"
					+ "last_name='" + user.getLastName() + "',"
					+ "email='" + user.getEmail() + "',"
					+ "username='" + user.getUsername() + "',"
					+ "password='" + user.getPassword() + "'"
					+ "WHERE user_id=" + user.getUserId(); 
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateName(int userId, String firstName, String lastName) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "UPDATE users SET "
					+ "first_name='" + firstName + "',"
					+ "last_name='" + lastName + "'"
					+ "WHERE user_id=" + userId;
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmail(int userId, String email) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "UPDATE users SET"
					+ "email='" + email + "'"
					+ "WHERE user_id=" + userId;
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUsername(int userId, String username) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "UPDATE users SET"
					+ "username='" + username + "'"
					+ "WHERE user_id=" + userId;
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * Future implementation should be email reset
	 */
	public void updatePassword(int userId, String password) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "UPDATE users SET"
					+ "password='" + password + "'"
					+ "WHERE user_id=" + userId;
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
