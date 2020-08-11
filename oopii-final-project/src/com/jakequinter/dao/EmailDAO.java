package com.jakequinter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jakequinter.models.Email;
import com.jakequinter.models.PhoneNumber;

public class EmailDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/oopii_final_project?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "rootroot";

	// email sql
	private static final String INSERT_EMAIL_SQL = "INSERT INTO email (fk_email_contact_contactid, email, type) VALUES (?, ?, ?)";
	private static final String SELECT_EMAIL_BY_ID = "SELECT emailid, fk_email_contact_contactid, email, type FROM email where emailid = ? AND fk_email_contact_contactid = ?";
	private static final String UPDATE_EMAIL_SQL = "UPDATE email set fk_email_contact_contactid = ?, email = ?, type = ? where emailid = ?";
	private static final String DELETE_EMAIL_SQL = "DELETE FROM email WHERE emailid = ?";

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

	/**
	 * get list of phonenumbers that belong to specific contact
	 * @throws Exception 
	 */
	public List<Email> getContactEmails(int emailId) {

		List<Email> emails = new ArrayList<Email>();

		try {
			// get connection from method 
			Connection connection = getConnection();

			// create blank Statement
			Statement statement = connection.createStatement();

			// send query to database and store results.
			String query = "select * from email where fk_email_contact_contactid = " + emailId;
			System.out.println("query: " + query);

			ResultSet resultSet = statement.executeQuery(query);
			List<Email> list = new ArrayList<Email>();

			// step through each row in the result set 
			while (resultSet.next()) {
				list.add(mapEmail(resultSet));
			}

			// print table of results.
			emails = list;

			// close the connection
			connection.close();

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

		return emails;

	}
	
	/**
	 * get phonenumber by phonenumberid
	 * @throws Exception 
	 */
	public Email getEmail(int emailId, int fkEmailContactId) {
		
		Email email = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_BY_ID);) {
			preparedStatement.setInt(1, emailId);
			preparedStatement.setInt(2, fkEmailContactId);
			// Step 3: Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultSet.next()) {
				String e_mail = resultSet.getString("email");
				String type = resultSet.getString("type");
				
				email = new Email(emailId, fkEmailContactId, e_mail, type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return email;
		
	}
	
	/**
	 * insert an email
	 * @throws Exception 
	 */
	public void addEmail(Email email) throws SQLException {
		System.out.println(INSERT_EMAIL_SQL);
		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMAIL_SQL)) {
			preparedStatement.setInt(1, email.getFkEmailContactId());
			preparedStatement.setString(2, email.getEmail());
			preparedStatement.setString(3, email.getType());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error in addEmail in EmailDAO");
			e.printStackTrace();
		} 
	}
	

	/**
	 * update an email
	 * @throws Exception 
	 */
	public boolean updateEmail(Email email) throws Exception {
		System.out.println(UPDATE_EMAIL_SQL);
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL_SQL);) {
			statement.setInt(1, email.getFkEmailContactId());
			statement.setString(2, email.getEmail());
			statement.setString(3, email.getType());
			statement.setInt(4, email.getEmailId());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	/**
	 * deletes an email
	 * @throws Exception 
	 */
	public boolean deleteEmail(int emailId) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMAIL_SQL);) {
			statement.setInt(1, emailId);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	protected Email mapEmail(ResultSet resultSet) throws SQLException {
		int emailId = resultSet.getInt("emailid");
		int fkEmailContactId = resultSet.getInt("fk_email_contact_contactid");
		String email = resultSet.getString("email");
		String type = resultSet.getString("type");

		return new Email(emailId, fkEmailContactId, email,type);
	}
}
