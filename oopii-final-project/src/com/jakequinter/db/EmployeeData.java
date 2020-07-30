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

import com.jakequinter.models.Employee;

public class EmployeeData {
	
	private String url;
	private String tableName;
	private String username;
	private String password;
	

	private static final String INSERT_USERS_SQL = "INSERT INTO employees2" + "  (firstName, lastName, address) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select id,firstname,lastname,address from employees2 where id =?";
	
	

	public EmployeeData(String dbName, String tableName, String username, String password) {
		this.tableName = tableName;
		this.username = username;
		this.password = password;
		this.url = "jdbc:derby:" + dbName;
	}
	
	public List<Employee> getEmployees() {
		
		List<Employee> result = new ArrayList<Employee>();
		
		try {
			// Get connection from method (to support overriding later to use DataSource)
			Connection connection = getConnection();
			
			// Create blank Statement
			Statement statement = connection.createStatement();
			
			// Send query to database and store results.
			String query = "SELECT * FROM " + tableName;
			ResultSet resultSet = statement.executeQuery(query);
			List<Employee> list = new ArrayList<Employee>();
			
			System.out.println("before while");
			// Step through each row in the result set and print cells
			while (resultSet.next()) {
				list.add(mapEmployee(resultSet));
			}
			System.out.println("after while");
			
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

	public Employee getEmployee(String id) {
		
//		Employee employee = null;
//		// Step 1: Establishing a Connection
//		try (Connection connection = getConnection();
//				// Step 2:Create a statement using connection object
//				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
//			preparedStatement.setString(1, id);
//			System.out.println(preparedStatement);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//
//			// Step 4: Process the ResultSet object.
//			while (rs.next()) {
//				String firstName = rs.getString("firstName");
//				String lastName = rs.getString("lastName");
//				String address = rs.getString("address");
//				employee = new Employee(id, firstName, lastName, address);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		return employee;
		
Employee result = null;
		
		try {
			// Get connection from method (to support overriding later to use DataSource)
			Connection connection = getConnection();
			
			// Send query to database and store results.
			String query = "SELECT * FROM " + tableName + " WHERE ID = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
						
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				result = mapEmployee(resultSet);
			}

			// Close the connection. In high-traffic servlet, be sure
			// Driver supports connection pooling.
			connection.close();
						
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		return result;
	}
	
	public void insertEmployee(Employee employee) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getAddress());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	protected Employee mapEmployee(ResultSet resultSet) throws SQLException {
		System.out.println(resultSet);
		int employeeID = resultSet.getInt("id");
		String firstName = resultSet.getString("firstname");
		String lastName = resultSet.getString("lastname");
		String address = resultSet.getString("address");
		
		return new Employee(employeeID, firstName, lastName, address);
	}

	protected Connection getConnection() throws Exception {

		// Establish network connection to database.
		Properties userInfo = new Properties();
		userInfo.put("user", username);
		userInfo.put("password", password);
		Connection connection = DriverManager.getConnection(url, userInfo);
				
		return (connection);
	}
}
