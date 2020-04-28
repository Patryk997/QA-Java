package com.qa.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	Order order;
	Order other;
	
	Order empty;
	
	@Before
	public void setUp() {
		order = new Order(24, new Customer(1, "Tom"), Date.valueOf("2020-04-20"), true, 345.99);
		other = new Order(24, new Customer(1, "Tom"), Date.valueOf("2020-04-20"), true, 345.99);
		empty = new Order();
	}
	
	@Test
	public void checkEqualityObjects() {
		assertTrue(order.equals(other));
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomer());
		order.setId(null);
		assertNull(order.getId());
		order.setCustomer(null);
		assertNull(order.getCustomer());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}
	
	@Test
	public void equalsWithNewObject() {
		assertFalse(order.equals(new Object()));
	}
	
	@Test
	public void compareOrderCustomer() {
		assertEquals(new Customer(1, "Tom"), order.getCustomer());
		assertNotEquals(new Customer(2, "Tom"), order.getCustomer());
	}
	
	@Test
	public void compareOrderId() {
		assertEquals(Integer.valueOf(24), order.getId());
		assertNotEquals(Integer.valueOf(2), order.getId());
	}
	
	@Test
	public void compareOrderPlaced() {
		assertEquals(Date.valueOf("2020-04-20"), order.getPlaced());
		assertNotEquals(Date.valueOf("2020-04-21"), order.getPlaced());
	}
	
	@Test
	public void compareItemValue() {
		assertEquals(Double.valueOf(345.99), order.getTotal());
		assertNotEquals(Double.valueOf(12.99), order.getTotal());
	}
	
	@Test
	public void compareOrderWithEmpty() {
		assertNotEquals(other, empty);
	}
	
	@Test
	public void compareOrderCustomerWithEmpty() {
		assertEquals(new Customer(1, "Tom"), order.getCustomer());
		assertNotEquals(new Customer(1, "Tom"), empty.getCustomer());
	}

}
