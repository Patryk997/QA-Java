package com.qa.controllers.subcontrollers;

import java.sql.SQLException;

import java.util.InputMismatchException;
import org.apache.log4j.Logger;

import com.qa.views.items.ItemDetailsView;
import com.qa.controllers.ItemsMenuController;
import com.qa.controllers.MenuController;



import com.qa.main.SessionHashMap;
import com.qa.models.Item;
import com.qa.persistence.service.ItemsService;
import com.qa.persistence.service.OrderItemService;
import com.qa.security.Authenticate;
import com.qa.utils.Utils;

public class ItemSubMenuController implements SubMenuController<Item> { 
	
	public static final Logger LOGGER = Logger.getLogger(ItemSubMenuController.class);
	
	MenuController menu;
	
	public static ItemSubMenuController itemsSubMenu;
	ItemsService itemsService = new ItemsService();
	OrderItemService orderItemService = new OrderItemService();
	
	public static ItemSubMenuController getItemsSubMenu() {
		if(itemsSubMenu == null)
			itemsSubMenu = new ItemSubMenuController();
		return itemsSubMenu;
	}
	
	public void add(int index, int orderId) {
		try {
			orderItemService.addItem(index, orderId);
			LOGGER.info("** Item added **");
		} catch (SQLException e) {
			LOGGER.info("Sorry, a problem occured, try again later");
		}
		
	}
	
	public void decreaseQuantity(int index, int orderId) {
		try {
			orderItemService.decreaseQuantity(index, orderId);
			LOGGER.info("** Item deleted **");
		} catch (SQLException e) {
			LOGGER.info("Sorry, a problem occured, try again later");
		}

	}
	
	public void deleteItemFromSystem(int index) {
		menu = ItemsMenuController.getItemsMenu();
		if(!Authenticate.isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			menu.selectMenuOptions();
		} else {
			try {
				itemsService.deleteItem(index);
				LOGGER.info("Item deleted from the system");
				menu.selectMenuOptions();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(int index) {
		menu = ItemsMenuController.getItemsMenu();
		if(!Authenticate.isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			menu.selectMenuOptions();
		} else {
			boolean flag = true;
			String name = "";
			double valueDouble = 0.00;
			
			while(flag) {
				LOGGER.info("Give a new name: | or select 'back' to return ");
				name = Utils.getInput();
				if(name.equals("back")) {
					flag = false;
					menu.selectMenuOptions();
				}
			    try {
					LOGGER.info("Give a new value:");
					String value = Utils.getInput();
	                valueDouble = Double.valueOf(value);
					flag = false;
				} catch (InputMismatchException e) {
					LOGGER.warn("Select a number in format e.g 33.33 or 9.00 and no more than 6 digits long");
				}
			}
					
			try {
				Item item = new Item(name, valueDouble);
				itemsService.updateItem(index, item);
				LOGGER.info("item updated");
			} catch (SQLException e) {
				LOGGER.info("Sorry, a problem occured, try again later");
			}
		}
		
	}
	
	@Override
	public Item selectById(int itemId) throws SQLException {
		Item item = itemsService.selectItem(itemId);
		return item;
	}
	
	@Override
	public String selectSubMenu() {
		int orderId = SessionHashMap.getSessionHashMap().get("orderId");
		Item item = null;
		int itemId = -1;
		boolean flag = true;
		menu = ItemsMenuController.getItemsMenu();
		
		LOGGER.info("Select ID item or 0 to return");
		while(flag) {
			String select = Utils.getInput();
			if(select == "0") {
				menu.selectMenuOptions();
				flag = false;
			}
			
			try {
				itemId = Utils.convertStringToInt(select);
				item = selectById(itemId);
				flag = false;
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info("This item does not exist");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				LOGGER.info("Select correct product ID");
			}
		}
	
		if(item == null) {
			LOGGER.warn("This item doesn not exist. Select ID from the list");
			menu.selectMenuOptions();	  // return to items list
		} else {
			ItemDetailsView.listItemDetails(item);
		}

		flag = true;
		while(flag) { 
			if(Authenticate.isAuthenticated()) {
				LOGGER.info("add to order [1]| decrease quantity [2] | back [3] | update in the system [4]");  
				LOGGER.info("delete from the system [5] | ");
			} else {
				LOGGER.info("add to order [1]| decrease quantity [2] | back [3]");  
			}

		    String next = Utils.getInput();
			switch(next) {
				case "1":
					add(itemId, orderId);			
					break;
					
				case "2":
					decreaseQuantity(itemId, orderId);
					break;
					
				case "3":
					flag = false;
					menu.selectMenuOptions();
					break;
			
				case "4":
					update(itemId);
					break;
							
				case "5":	
					deleteItemFromSystem(itemId);
					
				default:	
					LOGGER.warn(" -- Wrong command -- ");
			}
		}
			
		return "query ok";
	}

}
