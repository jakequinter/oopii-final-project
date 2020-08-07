package com.jakequinter.models;

public class Contact {
	
	private int contactId;
	private String firstName;
	private String lastName;
	private String fullName;
	
	public Contact(int contactId, String firstName, String lastName) {
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Contact(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}

}
