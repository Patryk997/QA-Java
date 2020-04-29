package com.qa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.models.Customer;
import com.qa.models.Order;
import com.qa.persistence.service.CrudService;
import com.qa.persistence.service.OrderService;

public class OrderServiceTest {
	
	CrudService<Order> orderService;
	
	@Before
	public void setUp() {
		orderService = new OrderService();
	}
	
	@Test
	public void OrderCRUD() throws SQLException {
		
		Customer customer = new Customer("Jazz");
		Order order = new Order();
		order.setCustomerId(16);
		final int id = orderService.create(order);
		Order other = orderService.select(id);
		assertEquals(Integer.valueOf(id), other.getId());
		
		Order toUpdate = new Order(id);
		boolean updated = orderService.update(toUpdate);
		Order updatedOne = orderService.select(id);
		assertTrue(updatedOne.isPaid());
		
		List<Order> orders = orderService.selectAll();
		Customer fourth = orders.get(0).getCustomer();
		String name = "Freddy";
		String customerName = fourth.getName();
		System.out.println(customerName);
		assertTrue(name.equals(customerName));
		
		boolean toDelete = orderService.delete(id);
		Order checkIfDeleted = orderService.select(id);
		assertNull(checkIfDeleted);
	}
	
	

}
