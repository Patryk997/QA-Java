package com.qa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.dto.Customer;
import com.qa.dto.Order;
import com.qa.services.CrudService;
import com.qa.services.OrderService;
import com.qa.utils.ConnectionMySQL;

public class OrderServiceTest {
	
	CrudService<Order> orderService; 
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
		orderService = new OrderService();
	}  
	
	@Test
	//@Ignore
	public void OrderCRUD() throws SQLException {
		
		Customer customer = new Customer("Jazz");
		Order order = new Order();
		order.setCustomerId(3);
		final int id = orderService.create(order);
		Order other = orderService.select(id);
		assertEquals(Integer.valueOf(id), other.getId());
		
		Order toUpdate = new Order(id);
		boolean updated = orderService.update(toUpdate);
		Order updatedOne = orderService.select(id);
		assertTrue(updatedOne.isPaid());
		
		List<Order> orders = orderService.selectAll();
		Customer first = orders.get(0).getCustomer();
		String name = "Garry";
		String customerName = first.getName();
		System.out.println(customerName);
		assertTrue(name.equals(customerName));
		
		boolean toDelete = orderService.delete(id);
		Order checkIfDeleted = orderService.select(id);
		assertNull(checkIfDeleted); 
	}
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}

}
