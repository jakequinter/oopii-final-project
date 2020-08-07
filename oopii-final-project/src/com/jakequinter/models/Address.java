package com.jakequinter.models;

public class Address {
	
	private int addressId;
	private int fkAddressContactId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	
	public Address(int addressId, int fkAddressContactId, String addressLine1, String addressLine2, String city,
			String state, String postalCode) {
		super();
		this.addressId = addressId;
		this.fkAddressContactId = fkAddressContactId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public Address(int fkAddressContactId, String addressLine1, String addressLine2, String city, String state,
			String postalCode) {
		super();
		this.fkAddressContactId = fkAddressContactId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getFkAddressContactId() {
		return fkAddressContactId;
	}

	public void setFkAddressContactId(int fkAddressContactId) {
		this.fkAddressContactId = fkAddressContactId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
