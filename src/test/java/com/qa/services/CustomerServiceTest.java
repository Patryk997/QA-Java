package com.qa.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.qa.models.Customer;
import com.qa.persistence.service.CrudService;
import com.qa.persistence.service.CustomerService;

public class CustomerServiceTest {
	
	CrudService<Customer> customerService;
	
	@Before
	public void setUp() {
		customerService = new CustomerService();
	}
	

	@Test
	public void customerCRUD() throws SQLException {
		
		Customer customer = new Customer("Tony");
		
		final int id = customerService.create(customer);
		Customer other = customerService.select(id);
		assertEquals(Integer.valueOf(id), other.getId());
		
		
		Customer toUpdate = new Customer(id, "Tony Robins");
		boolean updated = customerService.update(toUpdate);
		Customer updatedOne = customerService.select(id);
		assertEquals("Tony Robins", updatedOne.getName());
		
		
		List<Customer> customers = customerService.selectAll();
		Customer first = customers.get(0);
		assertEquals("Freddy", first.getName());
		assertTrue(customers.size() > 30);
		
		boolean toDelete = customerService.delete(id);
		Customer checkIfDeleted = customerService.select(id);
		assertNull(checkIfDeleted);
		
	}

	
}
