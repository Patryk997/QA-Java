package com.qa.controllers;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.controllers.subcontrollers.CustomerSubMenuController;
import com.qa.models.Customer;
import com.qa.persistence.service.CustomerService;
import com.qa.persistence.service.ItemsService;
import com.qa.persistence.service.other.OrderItemService;

 
@RunWith(MockitoJUnitRunner.class)
public class CustomersMenuControllerTest {
	
	CustomersMenuController cm;
	CustomersMenuController other;
	
	@Before
	public void setUp () {
		cm = new CustomersMenuController();
		other = new CustomersMenuController();
	}
	
	@Test
	//@Ignore
	public void selectMenuOptions() { 
		
		CustomerService service = mock(CustomerService.class);
		CustomersMenuController customerController = new CustomersMenuController(service);
		
		CustomersMenuController customerController2 = Mockito.spy(customerController);
		Mockito.doReturn(true).when(customerController2).isAuthenticated();
		Mockito.doReturn("1").when(customerController2).getInput();
		Mockito.doReturn("ok").when(customerController2).update();

		assertEquals("selected", customerController2.selectMenuOptions());
		 
	} 
	
	@Test
	//@Ignore
	public void selectMenuOptions2() { 
		
		Customer cus = new Customer("Tom");
		Customer cus1 = new Customer("Tom Tony");
		Customer cus2 = new Customer("Tom Tony Bravo");
		List<Customer> customers = Arrays.asList(cus, cus1, cus2);
		
		CustomerService service = mock(CustomerService.class);
		CustomersMenuController customerController = new CustomersMenuController(service);
		
		CustomersMenuController customerController2 = Mockito.spy(customerController);
		Mockito.doReturn(true).when(customerController2).isAuthenticated();
		Mockito.doReturn("3").when(customerController2).getInput();
		Mockito.doReturn(customers).when(customerController2).viewAll();

		assertEquals("selected", customerController2.selectMenuOptions());
		 
	}
	
	@Test
	public void update() {
		CustomerService service = mock(CustomerService.class);
		CustomersMenuController customerMock = mock(CustomersMenuController.class);
		
		CustomersMenuController customerController = new CustomersMenuController(service);
		customerController.setMenu(customerMock);
		
		CustomersMenuController customerController2 = Mockito.spy(customerController);
		
		Mockito.doNothing().when(customerController2).setMenu(CustomersMenuController.getCustomerMenu());
		Mockito.doNothing().when(customerController2).setService(new CustomerService());
		Mockito.doReturn(17).when(customerController2).getCustomerId();
		Mockito.doReturn("Mister X").when(customerController2).getInput();
		Mockito.doReturn("ok").when(customerMock).selectMenuOptions();
		
		
		assertEquals("updated", customerController2.update());
	
	}
	
	@Test
	public void viewAll() throws SQLException {
		
		CustomerService service = mock(CustomerService.class);
		CustomersMenuController customerMock = mock(CustomersMenuController.class);
		CustomerSubMenuController customerSubMock = mock(CustomerSubMenuController.class);
		
		CustomersMenuController customerController = new CustomersMenuController(service);
		customerController.setMenu(customerMock);
		customerController.setSubMenu(customerSubMock);
		CustomersMenuController customerController2 = Mockito.spy(customerController);
			
		
		Customer cus = new Customer("Tom");
		Customer cus1 = new Customer("Tom Tony");
		Customer cus2 = new Customer("Tom Tony Bravo");
		List<Customer> customers = Arrays.asList(cus, cus1, cus2);
		
		Mockito.doNothing().when(customerController2).setMenu(CustomersMenuController.getCustomerMenu());
		Mockito.doNothing().when(customerController2).setSubMenu(CustomerSubMenuController.getCustomerSubMenu());
		Mockito.doReturn(true).when(customerController2).isAuthenticated();
		Mockito.doReturn(customers).when(service).selectAll();
		Mockito.doReturn("ok").when(customerSubMock).selectSubMenu();
		assertFalse(customerController2.viewAll().isEmpty());
	}
	

}







