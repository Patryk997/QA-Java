package com.qa.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.main.ScannerHashMap;
import com.qa.main.SessionHashMap;
import com.qa.models.Item;
import com.qa.persistence.service.ItemsService;
import com.qa.persistence.service.OrderItemService;
import com.qa.persistence.service.OrderService;

public class OrdersMenuControllerTest {
	
//	OrderItemService orderItemService;
//	OrderItemService orderItemService2;
//	
//	OrderService orderService;
//	OrderService orderService2;
	
	OrdersMenuController ordersController;
	OrdersMenuController ordersController2;
	
	@Before
	public void setUp() {

		ordersController = new OrdersMenuController(new OrderItemService(), new OrderService());
		ordersController2 = Mockito.spy(ordersController);
	}
	
	@Test
	public void selectMenuOptions() {
		
		Mockito.doReturn(true).when(ordersController2).isAuthenticated();
		Mockito.doReturn(20).when(ordersController2).getOrderId();
		Mockito.doReturn("1").when(ordersController2).getInput();
		Mockito.doReturn(222.22).when(ordersController2).view(20);
		
		assertEquals("selected", ordersController2.selectMenuOptions());
	}

	@Test
	public void view() {
		Mockito.doReturn("updated").when(ordersController2).update();
		assertEquals(419.94, ordersController2.view(25), 0.01);
	}
	
	@Test
	public void update() {

		SessionHashMap map = SessionHashMap.getSessionHashMap();
		String id = "2";
		String next = "1";
		Mockito.doReturn(id).doReturn(next).when(ordersController2).getInput();
		Mockito.doReturn(20).when(ordersController2).getOrderId();
		Mockito.doReturn(2).when(ordersController2).convertStringToInt("2");
		Mockito.doReturn("view").when(ordersController2).orderItemDetailView(new Item(2, "Blouse", 59.99));
		Mockito.doNothing().when(ordersController2).delete(2, 20);
		
		
		assertEquals("updated", ordersController2.update());
	}
	
	@Test
	public void completeOrder() {
		
		Mockito.doReturn(0).when(ordersController2).getDefault();
		Mockito.doReturn(16).when(ordersController2).getCustomerId();
		Mockito.doNothing().when(ordersController2).putNewOrderId(222);
		Mockito.doReturn("selected").when(ordersController2).selectMenuOptions();
		assertEquals("completed", ordersController2.completeOrder(true, 20));
		
	}

	
}
