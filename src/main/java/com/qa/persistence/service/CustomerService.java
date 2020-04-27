package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Customer;
import com.qa.persistence.dao.CustomerDAO;
import com.qa.persistence.dao.CustomerDAOImpl;

public class CustomerService {

	private CustomerDAO customerDAO;
	
	public CustomerService() {
		customerDAO = new CustomerDAOImpl();
	}
	
	public int insertCustomer(Customer customer) throws SQLException {
		return customerDAO.insertCustomer(customer);
	}
	
	public boolean updateCustomer(Customer customer) throws SQLException {
		return customerDAO.updateCustomer(customer);
	}
	
	public Customer selectCustomer(int id) {
		return customerDAO.selectCustomer(id);
	}
	
	public List<Customer> selectAllCustomers() throws SQLException { 
		return customerDAO.selectAllCustomers();
	}
	
	public boolean deleteCustomer(int id) throws SQLException {
		return customerDAO.deleteCustomer(id); 
	}
	
	public boolean updateCustomerAsAdmin(Customer customer) throws SQLException {
		return customerDAO.updateCustomerAsAdmin(customer);
	}
}
