package com.qa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.dto.Customer;
import com.qa.dto.Order;
import com.qa.utils.ConnectionMySQL;

public class OrderDAOImpl implements OrderDAO {
		
	private static final String CREATE_ORDER_SQL = "INSERT INTO `Order` " + "(FK_Customer_ID) VALUES " +
	           "(?);"; 
	
	private static final String LIST_ALL_ORDERS_SQL = "SELECT o.Order_ID, o.placed, o.paid, o.total, " + 
			"c.name from `Order` as o INNER JOIN Customer c ON o.FK_Customer_ID = c.Customer_ID;";
	
	private static final String SELECT_ORDER_SQL  = "SELECT o.Order_ID, o.placed, o.total, o.paid, " + 
			"c.name from `Order` as o INNER JOIN Customer c ON o.FK_Customer_ID = c.Customer_ID WHERE o.Order_ID = ?;";
	
	private static final String DELETE_ORDER_FROM_SYSTEM_SQL = "DELETE from `Order` WHERE" + 
			" Order_ID = ?;";
	
	private static final String DELETE_ORDER_FROM_ORDER_ITEM_SQL = "DELETE from Order_Item WHERE" + 
			" FK_Order_ID = ?;";
	
	private static final String COMPLETE_ORDER_SQL = "UPDATE `Order` SET placed = CURDATE(), paid = ?"
			+ " WHERE Order_ID = ?;";
	
	
	
	@Override
	public int create(Order order) throws SQLException {  
		int rowInserted = 0;  
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_SQL,
			        Statement.RETURN_GENERATED_KEYS
					);
			preparedStatement.setInt(1, order.getCustomerId());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			    rowInserted = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		}
		return rowInserted;
	}
	
	@Override
	public List<Order> selectAll() {
		List<Order> ordersList = new ArrayList<Order>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(LIST_ALL_ORDERS_SQL);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("Order_ID");
				String name = rs.getString("name");
				double total = rs.getDouble("total");
				Date placed = rs.getDate("placed");
				boolean paid = rs.getBoolean("paid");
				Customer customer = new Customer(name);
				ordersList.add(new Order(id, customer, placed, paid, total));
			}

		} catch(Exception e) {
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return ordersList;
	}

	@Override
	public Order select(int orderId) {
		Order order = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ORDER_SQL);
			preparedStatement.setInt(1, orderId);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("Order_ID");
				String name = rs.getString("name");
				double total = rs.getDouble("total");
				boolean paid = rs.getBoolean("paid");
				Date placed = rs.getDate("placed");
				Customer customer = new Customer(name);
				order = new Order(id, customer, placed, paid, total);
			}

		} catch(Exception e) { 
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return order;
	}


	@Override
	public boolean delete(int orderId) throws SQLException {
		PreparedStatement preparedStatement = null;
		boolean rowDeleted = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_ORDER_FROM_ORDER_ITEM_SQL);
			preparedStatement.setInt(1, orderId);
			rowDeleted = preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		}
		
		try {
			Connection connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_ORDER_FROM_SYSTEM_SQL);
			preparedStatement.setInt(1, orderId);
			rowDeleted = preparedStatement.execute();
		
		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return rowDeleted;
	}

	@Override
	public boolean update(Order order) throws SQLException {
		boolean rowUpdated = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COMPLETE_ORDER_SQL,
			        Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, order.getId());
			preparedStatement.executeUpdate();
			rowUpdated = true;
			
		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		}
		return rowUpdated;
	}

	

}
