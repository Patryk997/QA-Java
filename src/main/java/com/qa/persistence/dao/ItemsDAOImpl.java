package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.models.Item;
import com.qa.utils.ConnectionMySQL;

public class ItemsDAOImpl implements ItemsDAO {
	
	private static final String SELECT_ITEM_BY_ID = "SELECT name, value from Item WHERE Item_ID = ?";
	private static final String SELECT_ALL_ITEMS = "SELECT * FROM Item";
	private static final String INSERT_NEW_ITEM = "INSERT INTO Item (name, value) VALUES (?,?);";
	private static final String UPDATE_ITEM_SQL = "UPDATE Item SET name = ?, value = ?"
			+ " WHERE Item_ID = ?;";
	private static final String DELETE_ITEM_FROM_ORDER_ITEMS_SQL = "DELETE from Order_Item WHERE FK_Item_ID = ?;";
	private static final String DELETE_ITEM_SQL = "DELETE from Item WHERE Item_ID = ?;";

	
	@Override
	public Item selectItem(int id) {
		Item item = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet rs  = null;

		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);	
			preparedStatement.setInt(1, id);		
			rs = preparedStatement.executeQuery();
					
			while(rs.next()) {
				String name = rs.getString("name");
				double value = rs.getDouble("value");
				item = new Item(name, value);
			}
			//connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return item;
	}

	@Override
	public List<Item> selectAllItems() {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet rs  = null;
		
        List<Item> items = new ArrayList<>();
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);

			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("Item_ID");
				String name = rs.getString("name");
				double value = rs.getDouble("value");
				items.add(new Item(id, name, value));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return items;
	}

	@Override
	public int insertItem(Item item) {
		int rowInserted = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ITEM,
			        Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, item.getName());
			preparedStatement.setDouble(2, item.getValue());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			    rowInserted = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowInserted;
	}

	@Override
	public int updateItem(int index, Item item) throws SQLException {
		int rowInserted = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_SQL,
			        Statement.RETURN_GENERATED_KEYS);
			
			System.out.println("name: " + item.getName() + " value: " + item.getValue());
			preparedStatement.setString(1, item.getName());
			preparedStatement.setDouble(2, item.getValue());
			preparedStatement.setInt(3, index);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			    rowInserted = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowInserted;
	}

	@Override
	public int deleteItem(int index) {
		int rowDeleted = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_FROM_ORDER_ITEMS_SQL,
			        Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, index);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			    rowDeleted = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_SQL,
			        Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, index);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			    rowDeleted = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}



}
