package com.qa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class ConnectionMySQL {  
	
	private static Connection connection;
	
	private static final String jdbcURLTest = "jdbc:mysql://XX.XX.XXX.XXX/inventory?user=root&password=root"; // test
    private static final String jdbcURLProd = "jdbc:mysql://XX.XX.XXX.XXX/inventory?user=root&password=root"; // prod
	
	//private static final String jdbcURLTest = "jdbc:mysql://XX.XX.XX.X/inventory?user=root&password=root"; // priv
	//private static final String jdbcURLProd = "jdbc:mysql://XX.XX.XX.X/inventory?user=root&password=root"; // prive
	
	private static boolean isTestable = false;
	private static String jdbcURL;
	
	public static void setTestable(boolean test) {
		isTestable = test;
	}
	
	public static Connection getConnection() { 
		
		if(isTestable)
			jdbcURL = jdbcURLTest;
		else
			jdbcURL = jdbcURLProd;
		
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			System.out.println("Sorry, service currently unavailable");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, service currently unavailable");
		}
			 
		return connection;
	}
	
	public static void closeConnection() {
		
		if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error occured when closing the conenction");
			}
	}

}
