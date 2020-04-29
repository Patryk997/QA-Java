package com.qa.controllers.subcontrollers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.qa.controllers.CustomersMenuController;
import com.qa.controllers.MenuController;
import com.qa.models.Customer;
import com.qa.persistence.service.CustomerService;
import com.qa.persistence.service.other.OrderItemService;


public class CustomerSubMenuControllerTest {
	
	MenuController menu;
	MenuController menu2;
	
	CustomerService customerService;
	CustomersMenuController customerController;
	CustomersMenuController customerController2;
	
	OrderItemService orderItemService;
	
	CustomerSubMenuController customerSubController;
	CustomerSubMenuController customerSubController2;
	

	
	@Before
	public void setUp() {
		customerService = new CustomerService();
		customerController = new CustomersMenuController();
		customerController2 = Mockito.spy(customerController);
	
		menu2 = mock(CustomersMenuController.class);
		
		orderItemService = mock(OrderItemService.class);
		
		customerSubController = new CustomerSubMenuController(orderItemService);
		customerSubController.setMenu(menu2);
		
		customerSubController2 = Mockito.spy(customerSubController);
	}
	
	@Test
	//@Ignore
	public void selectById() {
		Customer customer = customerService.select(5);
		Customer other = new Customer(5, "Admin");
		assertEquals(true, customer.equals(other));
	}
	
	@Test
	//@Ignore
	public void selectSubMenu() throws SQLException {

		when(menu2.selectMenuOptions()).thenReturn("ok");
			
		Mockito.doReturn(new CustomersMenuController()).when(customerSubController2).getCustomerMenu();
		Mockito.doReturn("select").when(customerController2).selectMenuOptions();
		Mockito.doReturn("8").doReturn("2").when(customerSubController2).getInput();
		Mockito.doNothing().when(customerSubController2).setMenu(CustomersMenuController.getCustomerMenu());
		Mockito.doReturn(8).when(customerSubController2).convertStringToInt("8");
		Mockito.doReturn(new Customer(8, "Freddy")).when(customerSubController2).selectById(8);
		assertEquals("query succeded", customerSubController2.selectSubMenu());

	}
	
	@Test
	//@Ignore
	public void deleteCustomerFromSystem() {
		
		Mockito.doReturn(8).when(customerSubController2).getCustomerId();
		Mockito.doReturn("8").when(customerSubController2).getInput();
		
		customerSubController2.deleteCustomerFromSystem(8);
		verify(customerSubController2, times(1)).deleteCustomerFromSystem(8);
	}
	

}
