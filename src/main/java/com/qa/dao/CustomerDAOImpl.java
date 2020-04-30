package com.qa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.dto.Customer;
import com.qa.utils.ConnectionMySQL;

public class CustomerDAOImpl implements CustomerDAO {
	
	private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO Customer " + "(name) VALUES " +
	           "(?);";            
	private static final String SELECT_CUSTOMER_BY_ID = "SELECT Customer_ID, FK_User_ID, name from Customer WHERE Customer_ID = ?";
	private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
	private static final String UPDATE_CUSTOMER_SQL = "UPDATE Customer SET name = ? WHERE Customer_ID = ?";
	private static final String DELETE_CUSTOMER_SQL = "DELETE FROM Customer WHERE Customer_ID = ?";
	private static final String UPDATE_CUSTOMER_USER_SQL = "UPDATE Customer SET FK_User_ID = ? WHERE Customer_ID = ?";

	@Override
	public int create(Customer customer) throws SQLException {
		int rowInserted = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL,
			        Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.executeUpdate(); 
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			    rowInserted = rs.getInt(1);
			} 
			
		} catch (Exception e) {
			System.out.println("Sorry, could not execute statement");
		}
		return rowInserted;
	}

	@Override
	public boolean update(Customer customer) throws SQLException {
		boolean rowUpdated = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setInt(2, customer.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Sorry, could not execute statement");
		}
		return rowUpdated;
		
	}

	@Override
	public Customer select(int id) {
		Customer customer = null;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);	
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();		
			while(rs.next()) {
				String name = rs.getString("name");
				int customerId = rs.getInt("Customer_ID");
				int userId = rs.getInt("FK_User_ID");
				customer = new Customer(customerId, name);
				customer.setUserId(userId);
				
			}
		} catch (Exception e) {
			System.out.println("Sorry, could not execute statement");
		}
		return customer;

	}

	@Override
	public List<Customer> selectAll() {
		List<Customer> customers = new ArrayList<>();	
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("Customer_ID");
				String name = rs.getString("name");
				customers.add(new Customer(id, name));
			}
		} catch (Exception e) {
			System.out.println("Sorry, could not execute statement");
		}
		return customers;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean rowDeleted = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL);
			preparedStatement.setInt(1, id); 
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Sorry, could not execute statement");
		}
	    return rowDeleted;	
		
	}

	@Override
	public boolean updateCustomerAsAdmin(Customer customer) throws SQLException {
		boolean rowUpdated = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_USER_SQL);
			preparedStatement.setInt(1, customer.getUserId());
			preparedStatement.setInt(2, customer.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Sorry, could not execute statement");
		}
		return rowUpdated;
	}
	

}
