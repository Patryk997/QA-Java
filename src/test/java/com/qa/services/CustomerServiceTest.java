package com.qa.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.qa.models.Customer;
import com.qa.persistence.service.CustomerService;

public class CustomerServiceTest {

	@Test
	public void listAllCustomers() throws SQLException {
		
		//CustomerService customerServices customerServices = mock(CustomerService.class);
		
		CustomerService customerService = new CustomerService();
		List<Customer> customers = new ArrayList<>();
		//when(customerService.viewAll()).thenReturn(customers);
		assertEquals(9, customerService.selectAllCustomers().size());
	}
}
