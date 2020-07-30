package com.jakequinter.db;

import java.sql.*;
import java.util.*;

import com.jakequinter.models.Employee;

/**
 * Creates "myDatabase" DB and "employees" table.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, Spring,
 * Hibernate/JPA, and Java programming</a>.
 */

public class EmbeddedDbCreator {
	// Driver class not needed in JDBC 4.0 (Java SE 6)
	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String protocol = "jdbc:derby:";
	private String username = "jakequinter";
	private String password = "password";
	private String dbName = "oopFinalProject";
	private String tableName = "employees2";
	private Properties userInfo;

	public EmbeddedDbCreator() {
		userInfo = new Properties();
		userInfo.put("user", username);
		userInfo.put("password", password);
	}

	public void createDatabase() {
		Employee[] employees = { new Employee(1, "Larry", "Ellison", "test"),
				new Employee(2, "Charles", "Phillips", "test2") };
		try {
			String dbUrl = protocol + dbName + ";create=true";
			try {
				Class.forName(driver);
			} catch(Exception _ex) {System.out.println("error");}
			Connection connection = DriverManager.getConnection(dbUrl, userInfo);
			Statement statement = connection.createStatement();
			String format = "VARCHAR(20)";
			String tableDescription = String.format(
					"CREATE TABLE %s" + "(id INT, firstname %s, lastname %s, address %s)", tableName,
					format, format, format);
			statement.execute(tableDescription);
			String template = String.format("INSERT INTO %s VALUES(?, ?, ?, ?)", tableName);
			PreparedStatement inserter = connection.prepareStatement(template);
			for (Employee e : employees) {
				inserter.setInt(1, e.getEmployeeID());
				inserter.setString(2, e.getFirstName());
				inserter.setString(3, e.getLastName());
				inserter.setString(4, e.getAddress());
				inserter.executeUpdate();
				System.out.printf("Inserted %s %s.%n", e.getFirstName(), e.getLastName(), e.getAddress());
			}
			inserter.close();
			connection.close();
		} catch (SQLException sqle) {
			// If table already exists, then skip everything else
			System.out.println("createDatabase in Embedded" + sqle);
		}
	}

	public void showTable() {
		try {
			String dbUrl = protocol + dbName;
			Connection connection;
			connection = DriverManager.getConnection(dbUrl, userInfo);
			Statement statement = connection.createStatement();
			String query = String.format("SELECT * FROM %s", tableName);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String address = resultSet.getString("address");

				System.out.printf("%s %s (%s, id=%s) earns $%,d per year.%n", firstName, lastName, id, address);
			}
			connection.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("showTable in Embedded");
		}
	}

	public static void main(String[] args) {
		EmbeddedDbCreator tester = new EmbeddedDbCreator();
		tester.createDatabase();
		tester.showTable();
	}
}

