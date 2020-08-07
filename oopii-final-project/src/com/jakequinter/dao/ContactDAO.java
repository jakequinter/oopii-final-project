package com.jakequinter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jakequinter.models.Contact;

public class ContactDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/oopii_final_project?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "rootroot";
	
	
	// contact sql
	private static final String INSERT_CONTACT_SQL = "INSERT INTO contact (firstname, lastname) VALUES (?, ?)";
	private static final String SELECT_CONTACT_BY_ID = "SELECT contactid, firstname, lastname FROM contact WHERE contactid = ?";
	private static final String UPDATE_CONTACT_SQL = "UPDATE contact set firstname = ?, lastname = ? WHERE contactid = ?";
	private static final String DELETE_CONTACT_SQL = "DELETE FROM contact WHERE contactid = ?";
	
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<Contact> getContacts() {
		
		List<Contact> result = new ArrayList<Contact>();
		
		try {
			// Get connection from method (to support overriding later to use DataSource)
			Connection connection = getConnection();
			
			// Create blank Statement
			Statement statement = connection.createStatement();
			
			// Send query to database and store results.
			String query = "SELECT * FROM contact";
			ResultSet resultSet = statement.executeQuery(query);
			List<Contact> list = new ArrayList<Contact>();
			
			// Step through each row in the result set and print cells
			while (resultSet.next()) {
				list.add(mapContact(resultSet));
			}
			
			// Print table of results.
			result = list;
			
			// Close the connection. In high-traffic servlet, be sure
			// Driver supports connection pooling.
			connection.close();
						
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		return result;
	}
	
	/**
	 * get a contact by contactid
	 * @throws Exception 
	 */
	public Contact getContact(int contactId) {
		
		Contact contact = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTACT_BY_ID);) {
			preparedStatement.setInt(1, contactId);
			// Step 3: Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				contact = new Contact(contactId, firstName, lastName);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return contact;
		
	}
	
	/**
	 * insert a contact
	 * @throws Exception 
	 */
	public void addContact(Contact contact) throws SQLException {
		System.out.println(INSERT_CONTACT_SQL);
		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
			preparedStatement.setString(1, contact.getFirstName());
			preparedStatement.setString(2, contact.getLastName());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("addContact in ContactDAO");
			e.printStackTrace();
		} 
	}
	

	/**
	 * update a contact
	 * @throws Exception 
	 */
	public boolean updateContact(Contact contact) throws Exception {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACT_SQL);) {
			statement.setString(1, contact.getFirstName());
			statement.setString(2, contact.getLastName());
			statement.setInt(3, contact.getContactId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	/**
	 * delete a contact
	 * @throws Exception 
	 */
	public boolean deleteContact(int contactId) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CONTACT_SQL);) {
			statement.setInt(1, contactId);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	

	protected Contact mapContact(ResultSet resultSet) throws SQLException {
		int contactId = resultSet.getInt("contactid");
		String firstName = resultSet.getString("firstname");
		String lastName = resultSet.getString("lastname");
		
		return new Contact(contactId, firstName, lastName);
	}
}
