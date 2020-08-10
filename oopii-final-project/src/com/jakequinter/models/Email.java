package com.jakequinter.models;

public class Email {

	private int emailId;
	private int fkEmailContactId;
	private String email;
	private String type;
	
	public Email(int emailId, int fkEmailContactId, String email, String type) {
		super();
		this.emailId = emailId;
		this.fkEmailContactId = fkEmailContactId;
		this.email = email;
		this.type = type;
	}

	public Email(int fkEmailContactId, String email, String type) {
		super();
		this.fkEmailContactId = fkEmailContactId;
		this.email = email;
		this.type = type;
	}

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public int getFkEmailContactId() {
		return fkEmailContactId;
	}

	public void setFkEmailContactId(int fkEmailContactId) {
		this.fkEmailContactId = fkEmailContactId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
