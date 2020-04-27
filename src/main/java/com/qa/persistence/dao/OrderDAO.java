package com.qa.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Order;

public interface OrderDAO {

	int createOrder (Order order) throws SQLException;
	Order selectOrder(int id);
	List<Order> selectAllOrders();
	boolean deleteOrder(int orderId) throws SQLException;
	boolean completeOrder(boolean paid, int orderId) throws SQLException; 
	boolean setTotal(double total, int orderId);
}
