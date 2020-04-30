package com.qa.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.dto.Item;

public class ItemTest {
	
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1, "T-shirt", 49.99);
		other = new Item(1, "T-shirt", 49.99);
	}
	
	@Test
	public void checkEqualityObjects() {
		assertTrue(item.equals(other));
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		
		item.setId(null);
		assertNull(item.getId());
		item.setName(null);
		assertNull(item.getName());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}
	
	@Test
	public void equalsWithNewObject() {
		assertFalse(item.equals(new Object()));
	}
	
	@Test
	public void compareItemName() {
		assertEquals("T-shirt", item.getName());
		assertNotEquals("Blouse", item.getName());
	}
	
	@Test
	public void compareItemId() {
		assertEquals(Integer.valueOf(1), item.getId());
		assertNotEquals(Integer.valueOf(2), item.getId());
	}
	
	@Test
	public void compareItemValue() {
		assertEquals(Double.valueOf(49.99), item.getValue());
		assertNotEquals(Double.valueOf(12.99), item.getValue());
	}
	
	@Test
	public void compareWithDifferentId() {
		other.setId(2);
		assertFalse(item.equals(other));
	}

}
