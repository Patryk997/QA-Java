package com.qa.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Item;


public interface ItemsDAO {
	
	int create(Item item) throws SQLException;
	Item select(int id);
	List<Item> selectAll();
	boolean delete(int index) throws SQLException;
	boolean update(Item item) throws SQLException;
	

}
