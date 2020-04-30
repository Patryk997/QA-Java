package com.qa.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.dto.Item;
import com.qa.services.ItemsService;
import com.qa.utils.ConnectionMySQL;

public class ItemsMenuControllerTest {
	
	ItemsService itemsService;
	ItemsService itemsService2;
	
	ItemsMenuController itemsController;
	ItemsMenuController itemsController2;
	
	@Before
	public void setUp() {
		
		ConnectionMySQL.setTestable(true);
		
		itemsService = new ItemsService();
		itemsService2 = Mockito.spy(itemsService);
		
		itemsController = new ItemsMenuController(new ItemsService());
		itemsController2 = Mockito.spy(itemsController);
	}
	
	@Test
	//@Ignore
	public void selectMenuOptions() {
		
		List<Item> items = Arrays.asList(new Item("item", 33.33), new Item("item2", 44.44));

		Mockito.doReturn(true).when(itemsController2).isAuthenticated();
		Mockito.doReturn("1").when(itemsController2).getInput();
		Mockito.doReturn(items).when(itemsController2).viewAll();

		assertEquals("selected", itemsController2.selectMenuOptions());
	}
	
	@Test
	//@Ignore
	public void add() {
		Mockito.doReturn(true).when(itemsController2).isAuthenticated();
		Mockito.doReturn("Pullover").doReturn("39.99").when(itemsController2).getInput();
		
		Mockito.doReturn(true).when(itemsController2).validateName("Pullover");
		Mockito.doReturn(39.99).when(itemsController2).convertStringToDouble("39.99");
		Mockito.doReturn("selected").when(itemsController2).selectMenuOptions();

		assertEquals("added", itemsController2.add());

	} 
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}

}
