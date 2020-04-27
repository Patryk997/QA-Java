package com.qa.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	private Customer other;
	
	@Before
	public void setUp() {
		customer = new Customer(1, "Tom");
		other = new Customer(1, "Tom");
	}
	
	@Test
	public void checkEqualityObjects() {
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getName());
		
		customer.setId(null);
		assertNull(customer.getId());
		customer.setName(null);
		assertNull(customer.getName());

	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(customer.equals(null));
	}
	
	@Test
	public void equalsWithNewObject() {
		assertFalse(customer.equals(new Object()));
	}
	
	@Test
	public void compareCustomerName() {
		assertEquals("Tom", customer.getName());
		assertNotEquals("Mike", customer.getName());
	}
	
	@Test
	public void compareWithDifferentId() {
		other.setId(2);
		assertFalse(customer.equals(other));
	}
	
	
}
