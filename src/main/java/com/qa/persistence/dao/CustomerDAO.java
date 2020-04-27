package com.qa.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Customer;

public interface CustomerDAO {
	
	int insertCustomer (Customer customer) throws SQLException;
	boolean updateCustomer(Customer customer) throws SQLException;
	Customer selectCustomer(int id);
	List<Customer> selectAllCustomers() throws SQLException;
	boolean deleteCustomer(int id) throws SQLException;
	boolean updateCustomerAsAdmin(Customer customer) throws SQLException ;
}
