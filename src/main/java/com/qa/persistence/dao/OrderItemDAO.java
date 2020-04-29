package com.qa.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Item;
import com.qa.models.OrderItem;



public interface OrderItemDAO {
	
	int create(OrderItem orderItem) throws SQLException;
	Item select(int itemId, int orderId) throws SQLException;

	int decreaseQuantity(OrderItem orderItem) throws SQLException;
	List<OrderItem> listOrderItems(int orderId) throws SQLException;
	int delete(int itemId, int orderId) throws SQLException;
	boolean setTotal(double total, int orderId);

}
