package com.qa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.dto.Item;
import com.qa.services.CrudService;
import com.qa.services.ItemsService;
import com.qa.utils.ConnectionMySQL;

public class ItemsServiceTest {
	
	CrudService<Item> itemsService;
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
		itemsService = new ItemsService();
	} 
	
	@Test 
	//@Ignore
	public void ItemsCRUD() throws SQLException { 
		
		Item item = new Item("Dress S", 99.99);
		final int id = itemsService.create(item);
		Item other = itemsService.select(id);
		assertEquals(Integer.valueOf(id), other.getId());
		
		Item toUpdate = new Item(id, "Dress XS", 79.99);
		boolean updated = itemsService.update(toUpdate);
		Item updatedOne = itemsService.select(id);
		assertEquals("Dress XS", updatedOne.getName());
		
		List<Item> items = itemsService.selectAll();
		Item first = items.get(0);
		assertEquals("dress name changed", first.getName());
		
		boolean toDelete = itemsService.delete(id);
		Item checkIfDeleted = itemsService.select(id);
		assertNull(checkIfDeleted);
	}
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}

}
