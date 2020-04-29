package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Order;
import com.qa.persistence.dao.OrderDAO;
import com.qa.persistence.dao.OrderDAOImpl;

public class OrderService implements CrudService<Order> {
	
	private OrderDAO orderDAO;
	
	public OrderService() {
		orderDAO = new OrderDAOImpl();
	}
	
	public int create(Order order) throws SQLException {
		return orderDAO.create(order);
	}


	public Order select(int id) {
		return orderDAO.select(id);
	}


	public List<Order> selectAll() {
		return orderDAO.selectAll();
	}


	public boolean delete(int id) throws SQLException {
		return orderDAO.delete(id);
	}
	
	
	
	public boolean update(Order order) throws SQLException {
		return orderDAO.update(order);
	}

//	public boolean setTotal(double total, int orderId) {
//		return orderDAO.setTotal(total, orderId);
//	}

}
