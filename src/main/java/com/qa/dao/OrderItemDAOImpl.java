package com.qa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qa.dto.Item;
import com.qa.dto.OrderItem;
import com.qa.utils.ConnectionMySQL;


public class OrderItemDAOImpl implements OrderItemDAO {


	private static final String ADD_ITEM_SQL = "INSERT INTO Order_Item " + "(FK_Item_ID, FK_Order_ID, Quantity) "
			+ "VALUES (?,?,1);";

	private static final String INCREASE_ITEM_QUANTITY_SQL = "UPDATE Order_Item  SET quantity = quantity + 1"
			+ " WHERE FK_Item_ID = ? AND FK_Order_ID = ?;";

	private static final String SELECT_ITEM_SQL = "SELECT COUNT(*) AS total from Order_Item WHERE FK_Item_ID = ? "
			+ "AND FK_Order_ID = ?;";

	private static final String DECREASE_ITEM_QUANTITY_SQL = "UPDATE Order_Item  SET quantity = GREATEST(quantity - 1, 0)"
			+ " WHERE FK_Item_ID = ? AND FK_Order_ID = ?;";

	private static final String LIST_ORDER_ITEMS_SQL = "SELECT i.Item_ID, i.value, i.name, ot.quantity from Item as i " + 
			"JOIN Order_Item ot ON i.Item_ID = ot.FK_Item_ID WHERE ot.FK_Order_ID = ?;";
	
	private static final String SELECT_ORDER_ITEM_SQL  = "SELECT i.value, i.name from Item as i " + 
			"INNER JOIN Order_Item ot ON i.Item_ID = ot.FK_Item_ID WHERE ot.FK_Item_ID = ? AND ot.FK_Order_ID = ?;";
	
	private static final String DELETE_ORDER_ITEM_SQL = "DELETE from Order_Item WHERE FK_Item_ID = ? AND FK_Order_ID = ?;";
	
	private static final String SET_TOTAL_SQL = "UPDATE `Order` SET total = ?"
			+ " WHERE Order_ID = ?;";
	
	
	@Override
	public int create(OrderItem orderItem) {   
		int rowSelected = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ITEM_SQL);
			preparedStatement.setInt(1, orderItem.getItemId());
			preparedStatement.setInt(2, orderItem.getOrderId());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				rowSelected = rs.getInt("total");
			}

			preparedStatement.close();
			if (rowSelected == 0) {
				
				try {
					preparedStatement = connection.prepareStatement(ADD_ITEM_SQL);
					preparedStatement.setInt(1, orderItem.getItemId());
					preparedStatement.setInt(2, orderItem.getOrderId());
					preparedStatement.executeUpdate();

				} catch (Exception e) {
					System.out.println("Sorry, could not execute the request");
				}

			} else {
				try {
					preparedStatement = connection.prepareStatement(INCREASE_ITEM_QUANTITY_SQL);
					preparedStatement.setInt(1, orderItem.getItemId());
					preparedStatement.setInt(2, orderItem.getOrderId());	
					preparedStatement.executeUpdate();
				} catch (Exception e) {
					System.out.println("Sorry, could not execute the request");
				}

			}

		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return rowSelected;
	}

	@Override
	public int decreaseQuantity(OrderItem orderItem) {
		int rowUpdated = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(DECREASE_ITEM_QUANTITY_SQL);
			preparedStatement.setInt(1, orderItem.getItemId());
			preparedStatement.setInt(2, orderItem.getOrderId());
			rowUpdated = preparedStatement.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    //try { connection.close(); } catch (Exception e) { /* ignored */ }
		}
		return rowUpdated;
		
	}

	@Override
	public List<OrderItem> listOrderItems(int orderId) throws SQLException {
		List<OrderItem> listOrderItems = new ArrayList<OrderItem>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(LIST_ORDER_ITEMS_SQL);
			preparedStatement.setInt(1, orderId);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("Item_ID");
				String name = rs.getString("name");
				double value = rs.getDouble("value");
				int quantity = rs.getInt("quantity");
				Item item = new Item(id, name, value);
				listOrderItems.add(new OrderItem(item, quantity));

			}

			
		} catch(Exception e) {
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		}
		return listOrderItems;
	}

	@Override
	public Item select(int itemId, int orderId) throws SQLException { 
		Item item = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionMySQL.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ORDER_ITEM_SQL);
			preparedStatement.setInt(1, itemId);
			preparedStatement.setInt(2, orderId);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				double value = rs.getDouble("value");
				item = new Item(name, value);
			}

			
		} catch(Exception e) {
			System.out.println("Sorry, could not execute the request");
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    //try { connection.close(); } catch (Exception e) { /* ignored */ }
		}
		return item;
	}

	@Override
	public int delete(int itemId, int orderId) throws SQLException {
		int rowDeleted = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM_SQL);

			preparedStatement.setInt(1, itemId);
			preparedStatement.setInt(2, orderId);
			rowDeleted = preparedStatement.executeUpdate();
	
		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		}
		return rowDeleted;
	}

	@Override
	public boolean setTotal(double total, int orderId) {
		boolean rowUpdated = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SET_TOTAL_SQL);	
			preparedStatement.setDouble(1, total);
			preparedStatement.setInt(2, orderId);
			preparedStatement.executeUpdate();
			rowUpdated = true;
			
		} catch (Exception e) {
			System.out.println("Sorry, could not execute the request");
		}
		return rowUpdated;
	}

}
