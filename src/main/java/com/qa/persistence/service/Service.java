package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Order;

public interface Service<T> {
	
	int create (T entity) throws SQLException;
	T select(int id);
	List<T> selectAll() throws SQLException;
	boolean delete(int entityId) throws SQLException;
	boolean update(T order) throws SQLException;

}
