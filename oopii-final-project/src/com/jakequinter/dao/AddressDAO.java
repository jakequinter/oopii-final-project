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

public class AddressDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/oopii_final_project?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "rootroot";
	
	
	// address sql
	private static final String INSERT_ADDRESS_SQL = "INSERT INTO address (fk_address_contact_contactid, addressline1, addressline2, city, state, postalcode) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ADDRESS_BY_ID = "SELECT addressid, fk_address_contact_contactid, addressline1, addressline2, city, state, postalcode from address where addressid = ? AND fk_address_contact_contactid = ?";
	private static final String UPDATE_ADDRESS_SQL = "UPDATE address set fk_address_contact_contactid = ?, addressline1 = ?, addressline2 = ?, city = ?, state = ?, postalcode = ? where addressid = ?";
	private static final String DELETE_ADDRESS_SQL = "DELETE FROM address WHERE addressid = ?";
	
	
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
	
	public List<Address> getContactAddresses(int addressId) {
		
		List<Address> result = new ArrayList<Address>();
				
				try {
					// Get connection from method (to support overriding later to use DataSource)
					Connection connection = getConnection();
					
					// Create blank Statement
					Statement statement = connection.createStatement();
					
					// Send query to database and store results.
					String query = "select * from address where fk_address_contact_contactid = " + addressId;
					System.out.println("query: " + query);
					
					ResultSet resultSet = statement.executeQuery(query);
					List<Address> list = new ArrayList<Address>();
					
					// Step through each row in the result set and print cells
					while (resultSet.next()) {
						list.add(mapAddress(resultSet));
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
	 * get an address by addressid
	 * @throws Exception 
	 */
	public Address getAddress(int addressId, int fkAddressContactId) {
		
		Address address = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_ID);) {
			preparedStatement.setInt(1, addressId);
			preparedStatement.setInt(2, fkAddressContactId);
			// Step 3: Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultSet.next()) {
				String addressLine1 = resultSet.getString("addressline1");
				String addressLine2 = resultSet.getString("addressline2");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String postalCode = resultSet.getString("postalcode");
				address = new Address(addressId, fkAddressContactId, addressLine1, addressLine2, city, state, postalCode);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return address;
		
	}
	
	/**
	 * insert an address
	 * @throws Exception 
	 */
	public void addAddress(Address address) throws SQLException {
		System.out.println(INSERT_ADDRESS_SQL);
		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS_SQL)) {
			preparedStatement.setInt(1, address.getFkAddressContactId());
			preparedStatement.setString(2, address.getAddressLine1());
			preparedStatement.setString(3, address.getAddressLine2());
			preparedStatement.setString(4, address.getCity());
			preparedStatement.setString(5, address.getState());
			preparedStatement.setString(6, address.getPostalCode());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("addNumber in ContactDAO");
			e.printStackTrace();
		} 
	}
	

	/**
	 * update an address
	 * @throws Exception 
	 */
	public boolean updateAddress(Address address) throws Exception {
		System.out.println(UPDATE_ADDRESS_SQL);
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ADDRESS_SQL);) {
			statement.setInt(1, address.getFkAddressContactId());
			statement.setString(2, address.getAddressLine1());
			statement.setString(3, address.getAddressLine2());
			statement.setString(4, address.getCity());
			statement.setString(5, address.getState());
			statement.setString(6, address.getPostalCode());
			statement.setInt(7, address.getAddressId());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	/**
	 * delete an address
	 * @throws Exception 
	 */
	public boolean deleteAddress(int addressId) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ADDRESS_SQL);) {
			statement.setInt(1, addressId);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	

	protected Address mapAddress(ResultSet resultSet) throws SQLException {
		int addressId = resultSet.getInt("addressid");
		int fkAddressContactId = resultSet.getInt("fk_address_contact_contactid");
		String addressLine1 = resultSet.getString("addressline1");
		String addressLine2 = resultSet.getString("addressline2");
		String city = resultSet.getString("city");
		String state = resultSet.getString("state");
		String postalCode = resultSet.getString("postalcode");
		
		return new Address(addressId, fkAddressContactId, addressLine1, addressLine2, city, state, postalCode);
	}
}
