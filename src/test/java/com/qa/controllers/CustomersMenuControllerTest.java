package com.qa.controllers;


import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.service.CustomerService;


@RunWith(MockitoJUnitRunner.class)
public class CustomersMenuControllerTest {
	
	@Test
	@Ignore
	public void selectMenuOptions() {
		
		CustomerService service = mock(CustomerService.class);
		CustomersMenuController customerController = new CustomersMenuController(service);
		
		CustomersMenuController customerController2 = Mockito.spy(customerController);
		Mockito.doReturn(true).when(customerController2).isAuthenticated();
		Mockito.doReturn("1").when(customerController2).getInput();
		Mockito.doNothing().when(customerController2).update();

		assertEquals("selected", customerController2.selectMenuOptions());
		
	}

}
