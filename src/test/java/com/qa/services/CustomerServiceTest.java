package com.qa.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.qa.dto.Customer;
import com.qa.services.CrudService;
import com.qa.services.CustomerService;
import com.qa.utils.ConnectionMySQL;

public class CustomerServiceTest {
	
	CustomerService customerService;
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
		customerService = new CustomerService();
	}
	
 
	@Test
	//@Ignore
	public void customerCRUD() throws SQLException {
		
		Customer customer = new Customer("Simon");	
		final int id = customerService.create(customer); 
		Customer other = customerService.select(id);
		assertEquals(Integer.valueOf(id), other.getId());
		
		
		Customer toUpdate = new Customer(id, "Simon Robins");
		toUpdate.setUserId(1);
		boolean updated = customerService.update(toUpdate);
		boolean updatedAsAdmin = customerService.updateCustomerAsAdmin(toUpdate);
		Customer updatedOne = customerService.select(id);
		assertEquals("Simon Robins", updatedOne.getName());
		assertEquals(updatedOne.getUserId(), 1);
		
		
		List<Customer> customers = customerService.selectAll();
		Customer first = customers.get(0);
		assertEquals("Tony Robins", first.getName());
		assertTrue(customers.size() > 30);
		
		boolean toDelete = customerService.delete(id);
		Customer checkIfDeleted = customerService.select(id);
		assertNull(checkIfDeleted);
		
	}
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}

	
}
