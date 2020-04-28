package com.qa.controllers;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.main.ScannerHashMap;
import com.qa.models.Item;
import com.qa.persistence.service.ItemsService;
import com.qa.utils.Utils;

public class ItemsMenuControllerTest {
	
	ItemsService itemsService;
	ItemsService itemsService2;
	
	ItemsMenuController itemsController;
	ItemsMenuController itemsController2;
	
	@Before
	public void setUp() {
		itemsService = new ItemsService();
		itemsService2 = Mockito.spy(itemsService);
		
		itemsController = new ItemsMenuController(new ItemsService());
		itemsController2 = Mockito.spy(itemsController);
	}
	
	@Test
	@Ignore
	public void selectMenuOptions() {

		Mockito.doReturn(true).when(itemsController2).isAuthenticated();
		Mockito.doReturn("1").when(itemsController2).getInput();
		Mockito.doNothing().when(itemsController2).viewAll();

		assertEquals("selected", itemsController2.selectMenuOptions());
	}
	
	@Test
	@Ignore
	public void add() {
		Mockito.doReturn(true).when(itemsController2).isAuthenticated();
		Mockito.doReturn("Pullover").doReturn("39.99").when(itemsController2).getInput();
		
		Mockito.doReturn(true).when(itemsController2).validateName("Pullover");
		Mockito.doReturn(39.99).when(itemsController2).convertStringToDouble("39.99");
		Mockito.doReturn("selected").when(itemsController2).selectMenuOptions();

		assertEquals("added", itemsController2.add());

	}

}
