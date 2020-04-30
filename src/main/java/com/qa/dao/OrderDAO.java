package com.qa.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.dto.Order;

public interface OrderDAO {

	int create (Order order) throws SQLException;
	Order select(int id);
	List<Order> selectAll();
	boolean delete(int orderId) throws SQLException;
	boolean update(Order order) throws SQLException;
	
	
	
	
}
