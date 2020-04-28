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


public class CustomerSubMenuControllerTest {
	
	MenuController menu;
	MenuController menu2;
	
	CustomerService customerService;
	CustomersMenuController customerController;
	CustomersMenuController customerController2;
	
	CustomerSubMenuController customerSubController;
	CustomerSubMenuController customerSubController2;
	

	
	@Before
	public void setUp() {
		customerService = new CustomerService();
		customerController = new CustomersMenuController();
		customerController2 = Mockito.spy(customerController);
	
		menu = new CustomersMenuController();
		menu2 = mock(CustomersMenuController.class);
		
		customerSubController = new CustomerSubMenuController(menu2);
		customerSubController2 = Mockito.spy(customerSubController);
	}
	
	@Test
	public void selectById() {
		Customer customer = customerService.select(8);
		Customer other = new Customer(8, "Freddy");
		assertEquals(true, customer.equals(other));
	}
	
	@Test
	public void selectSubMenu() throws SQLException {

		when(menu2.selectMenuOptions()).thenReturn("ok");
			
		Mockito.doReturn(new CustomersMenuController()).when(customerSubController2).getCustomerMenu();
		Mockito.doReturn("select").when(customerController2).selectMenuOptions();
		Mockito.doReturn("8").doReturn("2").when(customerSubController2).getInput();
		Mockito.doReturn(8).when(customerSubController2).convertStringToInt("8");
		Mockito.doReturn(new Customer(8, "Freddy")).when(customerSubController2).selectById(8);
		assertEquals("query succeded", customerSubController2.selectSubMenu());

	}
	
	@Test
	public void deleteCustomerFromSystem() {
		
		Mockito.doReturn(8).when(customerSubController2).getCustomerId();
		Mockito.doReturn("8").when(customerSubController2).getInput();
		
		customerSubController2.deleteCustomerFromSystem(8);
		verify(customerSubController2, times(1)).deleteCustomerFromSystem(8);
	}
	

}
