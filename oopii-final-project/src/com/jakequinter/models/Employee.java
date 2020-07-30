package com.jakequinter.models;

import java.io.Serializable;

/**
 * Simple representation of an employee. Used to seed database initially.
 * Immutable.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, Spring,
 * Hibernate/JPA, and Java programming</a>.
 */

public class Employee implements Serializable {

	private static final long serialVersionUID = 3773608782961342552L;

	private int employeeID;
	private String firstName, lastName, address;

	public Employee(int employeeID, String firstName, String lastName, String address) {
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public int getEmployeeID() {
		return (employeeID);
	}

	public String getFirstName() {
		return (firstName);
	}

	public String getLastName() {
		return (lastName);
	}
	
	public String getAddress() {
		return (address);
	}


}