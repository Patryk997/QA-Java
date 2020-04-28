package com.qa.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.main.ScannerHashMap;
import com.qa.main.SessionHashMap;
import com.qa.models.Item;
import com.qa.persistence.service.ItemsService;
import com.qa.persistence.service.OrderService;
import com.qa.persistence.service.other.OrderItemService;

public class OrdersMenuControllerTest {
	
//	OrderItemService orderItemService;
//	OrderItemService orderItemService2;
//	
	OrderService orderService;
//	OrderService orderService2;
	
	OrdersMenuController ordersController;
	OrdersMenuController ordersController2;
	
	@Before
	public void setUp() {

		//orderService = new OrderService();
		orderService = mock(OrderService.class);
		
		ordersController = new OrdersMenuController(new OrderItemService());
		ordersController.setService(orderService);
		
		ordersController2 = Mockito.spy(ordersController);
		
	}
	
	@Test
	//@Ignore
	public void selectMenuOptions() {
		
		Mockito.doReturn(true).when(ordersController2).isAuthenticated();
		Mockito.doReturn(20).when(ordersController2).getOrderId();
		Mockito.doReturn("1").when(ordersController2).getInput();
		Mockito.doReturn(222.22).when(ordersController2).view(20);
		
		assertEquals("selected", ordersController2.selectMenuOptions());
	}

	@Test
	//@Ignore
	public void view() {
		Mockito.doReturn("updated").when(ordersController2).update();
		assertEquals(419.94, ordersController2.view(25), 0.01);
	}
	
	@Test
	//@Ignore
	public void update() {

		//SessionHashMap map = SessionHashMap.getSessionHashMap();
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
		
		ordersController = new OrdersMenuController(new OrderItemService());
		ordersController.setService(orderService);
		ordersController.setMenu(CustomersMenuController.getCustomerMenu());
		
		ordersController2 = Mockito.spy(ordersController);
		
		Mockito.doReturn(0).when(ordersController2).getDefault();
		Mockito.doNothing().when(ordersController2).setService(new OrderService());
		Mockito.doNothing().when(ordersController2).setMenu(CustomersMenuController.getCustomerMenu());
		Mockito.doReturn(16).when(ordersController2).getCustomerId();
		Mockito.doNothing().when(ordersController2).putNewOrderId(222);
		Mockito.doReturn("selected").when(ordersController2).selectMenuOptions();
		assertEquals("completed", ordersController2.completeOrder(true, 20));
		
	}

	
}
