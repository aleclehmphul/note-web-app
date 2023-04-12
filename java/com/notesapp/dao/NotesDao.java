package com.notesapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.notesapp.model.Note;

public class NotesDao extends BaseDao {

	public ArrayList<Note> getAllNotes(int userId) {
		ArrayList<Note> notes = new ArrayList<>();
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			
			String sql = "SELECT * FROM notes";
			ResultSet results = statement.executeQuery(sql);
			
			while (results.next())
				notes.add(new Note(results.getInt("note_id"),
								results.getInt("user_id"),
								results.getString("note_header"),
								results.getString("note_body"),
								results.getTimestamp("created_at"),
								results.getTimestamp("last_edited")));
			
			results.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (notes.size() > 0)
			return notes;
		
		return null;
	}
	
	public Note getNote(int noteId) {
		Note note = null;
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			
			String sql = "SELECT * FROM notes WHERE note_id=" + noteId;
			ResultSet results = statement.executeQuery(sql);
			
			while (results.next())
				note = new Note(results.getInt("note_id"),
								results.getInt("user_id"),
								results.getString("note_header"),
								results.getString("note_body"),
								results.getTimestamp("created_at"),
								results.getTimestamp("last_edited"));
			
			results.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return note;
	}
	
	public void addNote(int user, String header, String body) {
		String sql = "INSERT INTO notes "
				+ "(user_id, note_header, note_body, created_at, last_edited)"
				+ "VALUES (?,?,?,now(),now())";
		
		try (Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(sql)
		) {
			statement.setInt(1, user);
			statement.setString(2, header);
			statement.setString(3, body);
			
			int rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteNote(int noteId) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			
			String sql = "DELETE FROM notes WHERE note_id=" + noteId;
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateNote(Note note) {
		try (Connection con = establishConnection();
			Statement statement = con.createStatement()
		) {
			String sql = "UPDATE notes SET "
					+ "note_header='" + note.getHeader() + "',"
					+ "note_body='" + note.getBody() + "',"
					+ "last_edited = now()"
					+ "WHERE note_id=" + note.getId();
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
