package com.notesapp.model;

import java.sql.Timestamp;

public class Note {

	private int id;
	private int user;
	private String header;
	private String body;
	private Timestamp createdAt;
	private Timestamp lastEdited;
	
	public Note(int id, int user, String header, String body, Timestamp createdAt, Timestamp lastEdited) {
		super();
		this.id = id;
		this.user = user;
		this.header = header;
		this.body = body;
		this.createdAt = createdAt;
		this.lastEdited = lastEdited;
	}
	
	// Use this constructor for updating notes
	public Note(int id, String header, String body) {
		super();
		this.id = id;
		this.header = header;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Timestamp lastEdited) {
		this.lastEdited = lastEdited;
	}
}
