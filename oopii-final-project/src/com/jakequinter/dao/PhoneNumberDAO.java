package com.jakequinter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jakequinter.models.Address;
import com.jakequinter.models.PhoneNumber;

public class PhoneNumberDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/oopii_final_project?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "rootroot";

	// phonenumber sql
	private static final String INSERT_PHONENUMBER_SQL = "INSERT INTO phonenumber (fk_phonenumber_contact_contactid, phonenumber, type) VALUES (?, ?, ?)";
	private static final String SELECT_PHONENUMBER_BY_ID = "SELECT phonenumberid, fk_phonenumber_contact_contactid, phonenumber, type FROM phonenumber where phonenumberid = ? AND fk_phonenumber_contact_contactid = ?";
	private static final String UPDATE_PHONENUMBER_SQL = "UPDATE phonenumber set fk_phonenumber_contact_contactid = ?, phonenumber = ?, type = ? where phonenumberid = ?";
	private static final String DELETE_PHONENUMBER_SQL = "DELETE FROM phonenumber WHERE phonenumberid = ?";

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
	public List<PhoneNumber> getContactPhoneNumbers(int phoneNumberId) {

		List<PhoneNumber> phoneNumber = new ArrayList<PhoneNumber>();

		try {
			// get connection from method 
			Connection connection = getConnection();

			// create blank Statement
			Statement statement = connection.createStatement();

			// send query to database and store results.
			String query = "select * from phonenumber where fk_phonenumber_contact_contactid = " + phoneNumberId;
			System.out.println("query: " + query);

			ResultSet resultSet = statement.executeQuery(query);
			List<PhoneNumber> list = new ArrayList<PhoneNumber>();

			// step through each row in the result set 
			while (resultSet.next()) {
				list.add(mapPhoneNumber(resultSet));
			}

			// print table of results.
			phoneNumber = list;

			// close the connection
			connection.close();

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

		return phoneNumber;

	}
	
	/**
	 * get phonenumber by phonenumberid
	 * @throws Exception 
	 */
	public PhoneNumber getPhoneNumber(int phoneNumberId, int fkPhoneNumberContactId) {
		
		PhoneNumber phoneNumber = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONENUMBER_BY_ID);) {
			preparedStatement.setInt(1, phoneNumberId);
			preparedStatement.setInt(2, fkPhoneNumberContactId);
			// Step 3: Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultSet.next()) {
				String number = resultSet.getString("phoneNumber");
				String type = resultSet.getString("type");
				
				phoneNumber = new PhoneNumber(phoneNumberId, fkPhoneNumberContactId, number, type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return phoneNumber;
		
	}
	
	/**
	 * insert a phonenumber
	 * @throws Exception 
	 */
	public void addPhoneNumber(PhoneNumber phoneNumber) throws SQLException {
		System.out.println(INSERT_PHONENUMBER_SQL);
		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHONENUMBER_SQL)) {
			preparedStatement.setInt(1, phoneNumber.getFkPhoneNumberContactId());
			preparedStatement.setString(2, phoneNumber.getPhoneNumber());
			preparedStatement.setString(3, phoneNumber.getType());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error in addPhoneNumber in PhoneNumberDAO");
			e.printStackTrace();
		} 
	}
	

	/**
	 * update a phonenumber
	 * @throws Exception 
	 */
	public boolean updatePhoneNumber(PhoneNumber phoneNumber) throws Exception {
		System.out.println(UPDATE_PHONENUMBER_SQL);
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PHONENUMBER_SQL);) {
			statement.setInt(1, phoneNumber.getFkPhoneNumberContactId());
			statement.setString(2, phoneNumber.getPhoneNumber());
			statement.setString(3, phoneNumber.getType());
			statement.setInt(4, phoneNumber.getPhoneNumberId());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	/**
	 * delete a phonenumber
	 * @throws Exception 
	 */
	public boolean deletePhoneNumber(int phoneNumberId) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PHONENUMBER_SQL);) {
			statement.setInt(1, phoneNumberId);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	protected PhoneNumber mapPhoneNumber(ResultSet resultSet) throws SQLException {
		int phoneNumberId = resultSet.getInt("phonenumberid");
		int dkPhoneNumberContactId = resultSet.getInt("fk_phonenumber_contact_contactid");
		String phoneNumber = resultSet.getString("phonenumber");
		String type = resultSet.getString("type");

		return new PhoneNumber(phoneNumberId, dkPhoneNumberContactId, phoneNumber,type);
	}
}
