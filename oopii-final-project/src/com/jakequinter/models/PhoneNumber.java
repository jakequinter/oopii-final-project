package com.jakequinter.models;

public class PhoneNumber {
	
	private int phoneNumberId;
	private int fkPhoneNumberContactId;
	private String phoneNumber;
	private String type;
	
	public PhoneNumber(int phoneNumberId, int fkPhoneNumberContactId, String phoneNumber, String type) {
		super();
		this.phoneNumberId = phoneNumberId;
		this.fkPhoneNumberContactId = fkPhoneNumberContactId;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}

	public PhoneNumber(int fkPhoneNumberContactId, String phoneNumber, String type) {
		super();
		this.fkPhoneNumberContactId = fkPhoneNumberContactId;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}

	public int getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(int phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public int getFkPhoneNumberContactId() {
		return fkPhoneNumberContactId;
	}

	public void setFkPhoneNumberContactId(int fkPhoneNumberContactId) {
		this.fkPhoneNumberContactId = fkPhoneNumberContactId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
