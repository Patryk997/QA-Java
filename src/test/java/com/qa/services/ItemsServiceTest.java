package com.qa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.models.Item;
import com.qa.persistence.service.CrudService;
import com.qa.persistence.service.ItemsService;

public class ItemsServiceTest {
	
	CrudService<Item> itemsService;
	
	@Before
	public void setUp() {
		itemsService = new ItemsService();
	}
	
	@Test
	public void ItemsCRUD() throws SQLException {
		
		Item item = new Item("This is dress extra slim", 99.99);
		final int id = itemsService.create(item);
		Item other = itemsService.select(id);
		assertEquals(Integer.valueOf(id), other.getId());
		
		Item toUpdate = new Item(id, "dress name changed", 79.99);
		boolean updated = itemsService.update(toUpdate);
		Item updatedOne = itemsService.select(id);
		assertEquals("dress name changed", updatedOne.getName());
		
		List<Item> items = itemsService.selectAll();
		Item first = items.get(0);
		assertEquals("Blouse", first.getName());
		
		boolean toDelete = itemsService.delete(id);
		Item checkIfDeleted = itemsService.select(id);
		assertNull(checkIfDeleted);
	}

}
