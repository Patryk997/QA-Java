package com.qa.controllers.subcontrollers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import com.qa.controllers.OrdersMenuController;
import com.qa.models.Customer;
import com.qa.models.Order;

import com.qa.persistence.service.OrderService;
import com.qa.persistence.service.other.OrderItemService;

public class OrderSubControllerTest {
	
	OrderSubMenuController orderSubMenuController;
	OrderSubMenuController orderSubMenuController2;
	
	
	@Before
	public void setUp() {
		orderSubMenuController = new OrderSubMenuController(new OrderItemService());
	}
	
	@Test
	public void deleteOrderFromSystem() {
		OrderService orderService = mock(OrderService.class);
		orderSubMenuController.setService(orderService);
		
		orderSubMenuController2 = Mockito.spy(orderSubMenuController);
		Mockito.doNothing().when(orderSubMenuController2).setService(new OrderService());
		Mockito.doReturn(9).when(orderSubMenuController2).getSessionId();
		assertFalse(orderSubMenuController2.deleteOrderFromSystem(9));
	}
	
	@Test
	public void selectById() throws SQLException {
		OrderService orderService = mock(OrderService.class);
		orderSubMenuController.setService(orderService);
		
		orderSubMenuController2 = Mockito.spy(orderSubMenuController);
		Mockito.doNothing().when(orderSubMenuController2).setService(new OrderService());
		
		Order order = new Order();
		
		order.setId(67);
		order.setTotal(101.97);
		
		Order order2 = orderSubMenuController2.selectById(67);
		System.out.println(order2.getPlaced().toString());
		assertTrue(order.equals(orderSubMenuController2.selectById(67)));
	}
	
	@Test
	public void selectSubMenu() throws SQLException {
		OrdersMenuController itemsMenuController = mock(OrdersMenuController.class);

		
		orderSubMenuController.setMenu(itemsMenuController);
		orderSubMenuController2 = Mockito.spy(orderSubMenuController);
		
		Mockito.doReturn(9).when(orderSubMenuController2).getSessionId();
		Mockito.doNothing().when(orderSubMenuController2).setMenu(OrdersMenuController.getOrderMenu());
		Mockito.doReturn(new Order(14, new Customer("Gary"), new Date(1587608600), true, 99.99))
			.when(orderSubMenuController2).selectById(14);
		Mockito.doReturn("14")
			.doReturn("2")
			.when(orderSubMenuController2).getInput();
		Mockito.doReturn("ok").when(itemsMenuController).selectMenuOptions();
		
		//Mockito.doReturn(true).when(orderSubMenuController2).isAuthenticated();
		//Mockito.doReturn("ok").when(orderSubMenuController2).update(anyInt());
		
		assertEquals("query ok", orderSubMenuController2.selectSubMenu());
	
	}

}
