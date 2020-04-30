package com.qa.services;

import java.sql.SQLException;
import java.util.List;

import com.qa.dto.Order;

public interface CrudService<T> {
	
	int create (T entity) throws SQLException;
	T select(int id);
	List<T> selectAll() throws SQLException;
	boolean delete(int entityId) throws SQLException;
	boolean update(T order) throws SQLException;

}
