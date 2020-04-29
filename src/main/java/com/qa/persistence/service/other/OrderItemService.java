package com.qa.persistence.service.other;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Item;
import com.qa.models.OrderItem;
import com.qa.persistence.dao.OrderItemDAO;
import com.qa.persistence.dao.OrderItemDAOImpl;

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
