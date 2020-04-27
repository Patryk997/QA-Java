package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Order;
import com.qa.persistence.dao.OrderDAO;
import com.qa.persistence.dao.OrderDAOImpl;

public class OrderService {
	
	private OrderDAO orderDAO;
	
	public OrderService() {
		orderDAO = new OrderDAOImpl();
	}
	
	public int createOrder(Order order) throws SQLException {
		return orderDAO.createOrder(order);
	}


	public Order selectOrder(int id) {
		return orderDAO.selectOrder(id);
	}


	public List<Order> selectAllOrders() {
		return orderDAO.selectAllOrders();
	}


	public boolean deleteOrder(int id) throws SQLException {
		return orderDAO.deleteOrder(id);
	}
	
	public boolean completeOrder(boolean paid, int orderId) throws SQLException {
		return orderDAO.completeOrder(paid, orderId);
	}

	public boolean setTotal(double total, int orderId) {
		return orderDAO.setTotal(total, orderId);
	}

}
