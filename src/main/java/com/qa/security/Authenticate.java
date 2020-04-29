package com.qa.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.qa.main.SessionHashMap;
import com.qa.utils.ConnectionMySQL;

public class Authenticate {
	
	public static final Logger LOGGER = Logger.getLogger(Authenticate.class);
	
	private static Authenticate authenticate;
	
	public static Authenticate getAuthenticate() {
		if(authenticate == null)
			authenticate = new Authenticate();
		return authenticate;
	}
	
	private static final String AUTHENTICATE_USER_SQL = "SELECT User_ID from User WHERE username = ? "
			+ "AND password = ? AND isAdmin = true;";

	public int login (String username, String password) {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int rowCounted = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(AUTHENTICATE_USER_SQL,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();		

			if (rs.next()) {
				rowCounted = rs.getInt("User_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}

		return rowCounted;
	}
	
	public boolean logout() {
		SessionHashMap.getSessionHashMap().replace("authenticated", 0);
		LOGGER.info("** succesfully logged out **");
		return true;
	}
	
	
	public static boolean isAuthenticated() {	
		int auth = SessionHashMap.getSessionHashMap().get("authenticated");
		if(auth > 0)
			return true;
		return false;
		
	}
}
