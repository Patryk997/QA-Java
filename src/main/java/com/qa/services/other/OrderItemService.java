package com.qa.services.other;

import java.sql.SQLException;
import java.util.List;

import com.qa.dao.OrderItemDAO;
import com.qa.dao.OrderItemDAOImpl;
import com.qa.dto.Item;
import com.qa.dto.OrderItem;

public class OrderItemService  {
	
	private OrderItemDAO orderItemDAO;
	
	public OrderItemService() {
		orderItemDAO = new OrderItemDAOImpl();
	}
	
//	public int addItem(int itemId, int orderId) throws SQLException {
//		return orderItemDAO.addItem(itemId, orderId);
//	}
	
	public int create(OrderItem orderItem) throws SQLException { 
		return orderItemDAO.create(orderItem);
} 
	
	public int decreaseQuantity(OrderItem orderItem) throws SQLException {
		return orderItemDAO.decreaseQuantity(orderItem);
	} 
	
	public List<OrderItem> listOrderItems(int orderId) throws SQLException {
		return orderItemDAO.listOrderItems(orderId);  
	}
	
	public Item select(int itemId, int orderId) throws SQLException {
		return orderItemDAO.select(itemId, orderId);
	}
	
	public int delete(int itemId, int orderId) throws SQLException  {
		return orderItemDAO.delete(itemId, orderId);
	}
	
	public boolean setTotal(double total, int orderId) {
		return orderItemDAO.setTotal(total, orderId);
	}

}
