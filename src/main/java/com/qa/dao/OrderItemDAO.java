package com.qa.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.dto.Item;
import com.qa.dto.OrderItem;



public interface OrderItemDAO {
	
	int create(OrderItem orderItem) throws SQLException;
	Item select(int itemId, int orderId) throws SQLException;

	int decreaseQuantity(OrderItem orderItem) throws SQLException;
	List<OrderItem> listOrderItems(int orderId) throws SQLException;
	int delete(int itemId, int orderId) throws SQLException;
	boolean setTotal(double total, int orderId);

}
