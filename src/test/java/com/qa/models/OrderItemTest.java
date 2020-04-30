package com.qa.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.dto.Item;
import com.qa.dto.OrderItem;

public class OrderItemTest {
	
	OrderItem orderItem;
	OrderItem other;
	
	@Before
	public void setUp() {
		orderItem = new OrderItem(new Item(1, "T-shirt", 29.99), 50);
		orderItem.setOrderItemId(20);
		
		other = new OrderItem(new Item(1, "T-shirt", 29.99), 50);
		other.setOrderItemId(20);
	}
	
	@Test
	public void checkEqualityObjects() {
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void settersTest() {
		assertNotNull(orderItem.getOrderItemId());
		assertNotNull(orderItem.getItem());
		orderItem.setItem(null);
		assertNull(orderItem.getItem());
		orderItem.setQuantity(50);
		assertNotNull(orderItem.getQuantity());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(orderItem.equals(null));
	}
	
	@Test

	public void equalsWithNewObject() {
		assertFalse(orderItem.equals(new Object()));
	}
	
	@Test
	public void compareOrderItem() {
		assertEquals(new Item(1, "T-shirt", 29.99), orderItem.getItem());
		assertNotEquals(new Item(1, "T-shirt", 39.99), orderItem.getItem());
		assertNotEquals(new Item("T-shirt", 29.99), orderItem.getItem());
	}
	
	@Test
	public void compareOrderItemId() {
		assertEquals(Integer.valueOf(20), orderItem.getOrderItemId());
		assertNotEquals(Integer.valueOf(2), orderItem.getOrderItemId());
	}
	
	@Test
	public void compareOrderItemQuantity() {
		assertEquals(Integer.valueOf(50), orderItem.getQuantity());
		assertNotEquals(Integer.valueOf(5), orderItem.getQuantity());
	}

}
