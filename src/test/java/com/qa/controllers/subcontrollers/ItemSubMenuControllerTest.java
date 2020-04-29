package com.qa.controllers.subcontrollers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.controllers.ItemsMenuController;
import com.qa.models.Item;
import com.qa.persistence.service.ItemsService;
import com.qa.persistence.service.other.OrderItemService;

public class ItemSubMenuControllerTest {
	
	ItemSubMenuController itemSubMenuController;
	ItemSubMenuController itemSubMenuController2;
	
	@Before
	public void setUp() {
		itemSubMenuController = new ItemSubMenuController(new OrderItemService());
	}
	
	@Test
	//@Ignore
	public void add() {
 
		itemSubMenuController2 = Mockito.spy(itemSubMenuController);
		itemSubMenuController2.add(4, 5);
		itemSubMenuController2.add(4, 5);
		itemSubMenuController2.add(4, 5);
		
		verify(itemSubMenuController2, times(3)).add(4, 5);
	}
	
	@Test
	//@Ignore
	public void decreaseQuantity() {
 
		itemSubMenuController2 = Mockito.spy(itemSubMenuController);
		itemSubMenuController2.decreaseQuantity(4, 5);
		itemSubMenuController2.decreaseQuantity(4, 5);
		itemSubMenuController2.decreaseQuantity(4, 5);
		
		verify(itemSubMenuController2, times(3)).decreaseQuantity(4, 5);
	}
	
	@Test
	//@Ignore
	public void deleteItemFromSystem() {
		
		ItemsMenuController itemsMenuController = mock(ItemsMenuController.class);
		ItemsService itemsService = mock(ItemsService.class);
		
		itemSubMenuController.setMenu(itemsMenuController);
		itemSubMenuController.setService(itemsService);
		itemSubMenuController2 = Mockito.spy(itemSubMenuController);
		
		//when(itemsMenuController.selectMenuOptions()).thenReturn("ok");
		
		Mockito.doNothing().when(itemSubMenuController2).setMenu(ItemsMenuController.getItemsMenu());
		Mockito.doReturn(true).when(itemSubMenuController2).isAuthenticated();
		Mockito.doReturn("ok").when(itemsMenuController).selectMenuOptions();
		
		itemSubMenuController2.deleteItemFromSystem(20);
		verify(itemSubMenuController2, times(1)).deleteItemFromSystem(20);
		
	}
	
	@Test
	//@Ignore
	public void update() {
		ItemsMenuController itemsMenuController = mock(ItemsMenuController.class);
		ItemsService itemsService = mock(ItemsService.class);
		
		itemSubMenuController.setMenu(itemsMenuController);
		itemSubMenuController.setService(itemsService);
		itemSubMenuController2 = Mockito.spy(itemSubMenuController);
		
		Mockito.doReturn(true).when(itemSubMenuController2).isAuthenticated();
		Mockito.doReturn("New suit case extra small")
			.doReturn("33.99")
			.when(itemSubMenuController2).getInput();
		assertEquals("updated", itemSubMenuController2.update(37));
	}
	
	@Test
	//@Ignore
	public void selectById() throws SQLException {
		ItemsMenuController itemsMenuController = mock(ItemsMenuController.class);
		ItemsService itemsService = mock(ItemsService.class);

		itemSubMenuController.setService(itemsService);
		itemSubMenuController2 = Mockito.spy(itemSubMenuController);
		
		Item item = new Item(5, "Pullover", 39.99);
		Item other = itemSubMenuController2.selectById(5);
		assertTrue(item.equals(other));
	}
	
	@Test
	//@Ignore
	public void selectSubMenu() {
		ItemsMenuController itemsMenuController = mock(ItemsMenuController.class);
		ItemsService itemsService = mock(ItemsService.class);
		
		itemSubMenuController.setMenu(itemsMenuController);
		itemSubMenuController.setService(itemsService);
		itemSubMenuController2 = Mockito.spy(itemSubMenuController);
		
		Mockito.doReturn(9).when(itemSubMenuController2).getSessionId();
		Mockito.doNothing().when(itemSubMenuController2).setMenu(ItemsMenuController.getItemsMenu());
		Mockito.doReturn("2")
			.doReturn("3")
			.when(itemSubMenuController2).getInput();
		Mockito.doReturn("ok").when(itemsMenuController).selectMenuOptions();
		Mockito.doReturn(true).when(itemSubMenuController2).isAuthenticated();
		Mockito.doReturn("ok").when(itemSubMenuController2).update(anyInt());
		
		assertEquals("query ok", itemSubMenuController2.selectSubMenu());
	
	}
	

}
