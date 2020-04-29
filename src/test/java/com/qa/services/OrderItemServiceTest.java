package com.qa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.models.Item;
import com.qa.models.Order;
import com.qa.models.OrderItem;
import com.qa.persistence.service.OrderService;
import com.qa.persistence.service.other.OrderItemService;

public class OrderItemServiceTest {

	OrderItemService orderItemService;
	
	@Before
	public void setUp() {
		orderItemService = new OrderItemService();
	}
	
	@Test
	public void orderItemCRUD() throws SQLException {
		
		int itemId = 2;
		//Item item = new Item(itemId, "Blouse", 59.99);
		int orderId = 9; 
		OrderItem orderItem = new OrderItem(itemId, orderId);
		orderItemService.create(orderItem);
		
		List<OrderItem> orderItems = orderItemService.listOrderItems(orderId);
		Item itemFromOrder = orderItems.get(0).getItem();
		String itemName = "New Coat XXL for men";
		String itemName2 = itemFromOrder.getName();
		assertTrue(itemName.equals(itemName2));
		
		Item selectItem = orderItemService.select(itemId, orderId);
		String name = "Blouse";
		String name2 = selectItem.getName();
		assertTrue(name.equals(name2));
		
		int quantity1 = orderItems.get(1).getQuantity();
		orderItemService.decreaseQuantity(orderItem);
		List<OrderItem> orderItems2 = orderItemService.listOrderItems(orderId);
		int quantity2 = orderItems2.get(1).getQuantity() + 1;
		assertEquals(quantity1, quantity2);
		
		OrderItem newOrder = new OrderItem(3, orderId);
		orderItemService.create(newOrder);
		orderItemService.delete(3, orderId);
		Item nullItem = orderItemService.select(3, orderId);
		assertNull(nullItem);
		
		boolean setTotal = orderItemService.setTotal(99.99, 21);
		
		OrderService orderService = new OrderService();
		Order order = orderService.select(21);
		assertEquals(99.99, order.getTotal(), 0.01);
		assertTrue(setTotal);
			
	}
}
