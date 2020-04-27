package com.qa.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Item;


public interface ItemsDAO {

	Item selectItem(int id);
	List<Item> selectAllItems();
	int insertItem(Item item) throws SQLException;
	int updateItem(int index, Item item) throws SQLException;
	int deleteItem(int index) throws SQLException;

}
