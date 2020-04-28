package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Customer;
import com.qa.persistence.dao.CustomerDAO;
import com.qa.persistence.dao.CustomerDAOImpl;

public class CustomerService implements CRUDService<Customer> {

	private CustomerDAO customerDAO;
	
	public CustomerService() {
		customerDAO = new CustomerDAOImpl();
	}
	
	public int create(Customer customer) throws SQLException {
		return customerDAO.create(customer);
	}
	
	public boolean update(Customer customer) throws SQLException {
		return customerDAO.update(customer);
	}
	
	public Customer select(int id) {
		return customerDAO.select(id);
	}
	
	public List<Customer> selectAll() throws SQLException { 
		return customerDAO.selectAll();
	}
	
	public boolean delete(int id) throws SQLException {
		return customerDAO.delete(id); 
	}
	
	public boolean updateCustomerAsAdmin(Customer customer) throws SQLException {
		return customerDAO.updateCustomerAsAdmin(customer);
	}
}
