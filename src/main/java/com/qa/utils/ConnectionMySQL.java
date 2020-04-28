package com.qa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
	
	// java -jar QA-Java-jar-with-dependencies.jar
	
	private static Connection connection;
	
	private static final String jdbcURL = "jdbc:mysql://34.89.125.175/inventory?user=root&password=root";
	//private static final String jdbcURL = "jdbc:mysql://35.197.207.145/inventory?user=root&password=root";
	//private static final String jdbcURL = "jdbc:mysql://10.92.48.3/inventory?user=root&password=root";
	
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			System.out.println("Sorry, service currently unavailable");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return connection;
	}
	
	public static void closeConnection() {
		
		if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
