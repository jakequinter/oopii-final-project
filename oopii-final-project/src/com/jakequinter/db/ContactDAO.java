package com.jakequinter.db;

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
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "itI90EtBMtwtRSw*";
	
	
	// contacts sql
	private static final String SELECT_ALL_USERS = "select * from contacts";

	private static final String INSERT_CONTACTS_SQL = "INSERT INTO contacts" + "  (firstname, lastname, address) VALUES (?, ?, ?)";
	private static final String SELECT_USER_BY_ID = "select id,firstname,lastname,address from contacts where id =?";
	private static final String UPDATE_CONTACTS_SQL = "UPDATE contacts set firstName = ?,lastName = ?, address = ? where id = ?";
	private static final String DELETE_CONTACTS_SQL = "DELETE FROM contacts WHERE id = ?";
	
	
	public List<Contact> getContacts() {
		
//		List<Contact> contacts = new ArrayList<>();
//		// Step 1: Establishing a Connection
//		try (Connection connection = getConnection();
//
//				// Step 2:Create a statement using connection object
//			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
//			System.out.println(preparedStatement);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//
//			// Step 4: Process the ResultSet object.
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String firstname = rs.getString("firstname");
//				String lastname = rs.getString("lastname");
//				String address = rs.getString("address");
//				contacts.add(new Contact(id, firstname, lastname, address));
//			}
//		} catch (Exception e) {
//			System.out.println("Error: getContacts");
//			e.printStackTrace();
//		}
//		return contacts;
List<Contact> result = new ArrayList<Contact>();
		
		try {
			// Get connection from method (to support overriding later to use DataSource)
			Connection connection = getConnection();
			
			// Create blank Statement
			Statement statement = connection.createStatement();
			
			// Send query to database and store results.
			String query = "SELECT * FROM contacts";
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
	

	public Contact getContact(int id) {
		
		Contact contact = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("address");
				contact = new Contact(id, firstName, lastName, address);
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
	
	public void addContact(Contact contact) throws SQLException {
		System.out.println(INSERT_CONTACTS_SQL);
		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACTS_SQL)) {
//			preparedStatement.setInt(1, contact.getId());
			preparedStatement.setString(1, contact.getFirstName());
			preparedStatement.setString(2, contact.getLastName());
			preparedStatement.setString(3, contact.getAddress());
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
				PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACTS_SQL);) {
			statement.setString(1, contact.getFirstName());
			statement.setString(2, contact.getLastName());
			statement.setString(3, contact.getAddress());
			statement.setInt(4, contact.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean deleteContact(int id) throws Exception {
		boolean deletedContact;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CONTACTS_SQL);) {
			statement.setInt(1, id);
			deletedContact = statement.executeUpdate() > 0;
		}
		return deletedContact;
	}

	protected Contact mapContact(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String firstName = resultSet.getString("firstname");
		String lastName = resultSet.getString("lastname");
		String address = resultSet.getString("address");
		
		return new Contact(id, firstName, lastName, address);
	}

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
}
