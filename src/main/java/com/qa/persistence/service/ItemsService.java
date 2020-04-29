package com.qa.persistence.service;

import java.sql.SQLException;
import java.util.List;

import com.qa.models.Item;
import com.qa.persistence.dao.ItemsDAO;
import com.qa.persistence.dao.ItemsDAOImpl;

public class ItemsService implements Service<Item> {
	
	private ItemsDAO itemsDAO;
	
	public ItemsService() {
		this.itemsDAO = new ItemsDAOImpl();
	}
	
	public Item select(int id) {
		return itemsDAO.select(id);
	}
	
	public List<Item> selectAll(){
		return itemsDAO.selectAll();
	}
	
	public int create(Item item) throws SQLException {
		return itemsDAO.create(item);
	}
	
	public boolean update(Item item) throws SQLException {
		return itemsDAO.update(item);
	}
	
	public boolean delete(int index) throws SQLException{
		return itemsDAO.delete(index);
	}

}
