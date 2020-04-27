package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Item;
import com.qa.persistence.dao.ItemsDAO;
import com.qa.persistence.dao.ItemsDAOImpl;

public class ItemsService {
	
	private ItemsDAO itemsDAO;
	
	public ItemsService() {
		this.itemsDAO = new ItemsDAOImpl();
	}
	
	public Item selectItem(int id) {
		return itemsDAO.selectItem(id);
	}
	
	public List<Item> selectAllItems(){
		return itemsDAO.selectAllItems();
	}
	
	public int insertItem(Item item) throws SQLException {
		return itemsDAO.insertItem(item);
	}
	
	public int updateItem(int index, Item item) throws SQLException {
		return itemsDAO.updateItem(index, item);
	}
	
	public int deleteItem(int index) throws SQLException{
		return itemsDAO.deleteItem(index);
	}

}
