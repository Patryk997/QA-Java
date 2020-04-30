package com.qa.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.dto.Item;


public interface ItemsDAO {
	
	int create(Item item) throws SQLException;
	Item select(int id);
	List<Item> selectAll();
	boolean delete(int index) throws SQLException;
	boolean update(Item item) throws SQLException;
	

}
