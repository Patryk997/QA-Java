package com.qa.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.dto.Customer;

public interface CustomerDAO {
	
	int create (Customer customer) throws SQLException;
	Customer select(int id);
	List<Customer> selectAll() throws SQLException;
	boolean delete(int id) throws SQLException;
	boolean update(Customer customer) throws SQLException;
	
	
	
	boolean updateCustomerAsAdmin(Customer customer) throws SQLException ;
}
