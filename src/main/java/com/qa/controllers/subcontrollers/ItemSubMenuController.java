package com.qa.controllers.subcontrollers;

import java.sql.SQLException;

import java.util.InputMismatchException;
import org.apache.log4j.Logger;

import com.qa.views.items.ItemDetailsView;
import com.qa.controllers.ItemsMenuController;
import com.qa.controllers.MenuController;
import com.qa.dto.Item;
import com.qa.dto.OrderItem;
import com.qa.main.SessionHashMap;
import com.qa.security.Authenticate;
import com.qa.services.CrudService;
import com.qa.services.ItemsService;
import com.qa.services.other.OrderItemService;
import com.qa.utils.Utils;

public class ItemSubMenuController implements SubMenuController<Item> { 
	
	public static final Logger LOGGER = Logger.getLogger(ItemSubMenuController.class);
	
	MenuController menu;
	CrudService service;
	OrderItemService orderItemService;
	
	public void setMenu(MenuController menu) {
		this.menu = menu;
	} 
	
	public void setService(CrudService service) {
		this.service = service;
	}
	
	public ItemSubMenuController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
	
	public static ItemSubMenuController itemsSubMenu;
	
	//OrderItemService orderItemService = new OrderItemService();
	
	public static ItemSubMenuController getItemsSubMenu() {
		if(itemsSubMenu == null)
			itemsSubMenu = new ItemSubMenuController(new OrderItemService());
		return itemsSubMenu;
	}
	
	
	
	public void add(int index, int orderId) {
		try {
			OrderItem orderItem = new OrderItem(index, orderId);
			orderItemService.create(orderItem);
			LOGGER.info("** Item added **");
		} catch (SQLException e) {
			LOGGER.info("Sorry, a problem occured, try again later");
		}
		
	}
	
	public void decreaseQuantity(int index, int orderId) {
		try {
			OrderItem orderItem = new OrderItem(index, orderId);
			orderItemService.decreaseQuantity(orderItem);
			LOGGER.info("** Item deleted **");
		} catch (SQLException e) {
			LOGGER.info("Sorry, a problem occured, try again later");
		}

	}
	
	public void deleteItemFromSystem(int index) {
		setMenu(ItemsMenuController.getItemsMenu());
		if(!isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			menu.selectMenuOptions();
		} else {
			try {
				service.delete(index);
				LOGGER.info("Item deleted from the system");
				menu.selectMenuOptions();
			} catch (SQLException e) {
				LOGGER.info("Problem occured when deleting the item");
			}
		}
	}
	
	public boolean isAuthenticated() {
		return Authenticate.isAuthenticated();
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public String update(int index) {
		setMenu(ItemsMenuController.getItemsMenu());
		setService(new ItemsService());
		if(!isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			menu.selectMenuOptions();
		} else {
			boolean flag = true;
			String name = "";
			double valueDouble = 0.00;
			
			while(flag) {
				LOGGER.info("Give a new name: | or select 'back' to return ");
				name = getInput();
				if(name.equals("back")) {
					flag = false;
					menu.selectMenuOptions();
				}
			    try {
					LOGGER.info("Give a new value:");
					String value = getInput();
	                valueDouble = Double.valueOf(value);
					flag = false;
				} catch (InputMismatchException e) {
					LOGGER.warn("Select a number in format e.g 33.33 or 9.00 and no more than 6 digits long");
				}
			}
					
			try {
				Item item = new Item(index, name, valueDouble);
				service.update(item);
				LOGGER.info("** Item updated ***");
			} catch (SQLException e) {
				LOGGER.info("Sorry, a problem occured, try again later");
			}
		}
		return "updated";
		
	}
	
	@Override
	public Item selectById(int itemId) throws SQLException {
		setService(new ItemsService());
		Object item2 = service.select(itemId);
		Item item = (Item) item2;
		return item;
	}
	
	public int getSessionId() {
		return SessionHashMap.getSessionHashMap().get("orderId");
	}
	
	public int convertStringToInt(String select) {
		return Utils.convertStringToInt(select);
	}
	
	@Override
	public String selectSubMenu() { 
		int orderId = getSessionId();
		Item item = null;
		int itemId = -1;
		boolean flag = true;
		setMenu(ItemsMenuController.getItemsMenu());
		
		LOGGER.info("Select ID item or 0 to return");
		while(flag) {
			String select = getInput();
			if(select == "0") {
				flag = false;
				menu.selectMenuOptions();
			}
			
			try {
				itemId = convertStringToInt(select);
				item = selectById(itemId);
				flag = false;
			} catch (SQLException e) {
				LOGGER.info("This item does not exist");
			} catch (NumberFormatException e) {
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
			if(isAuthenticated()) {
				LOGGER.info("add to order [1]| decrease quantity [2] | back [3] | update in the system [4]");  
				LOGGER.info("delete from the system [5] | ");
			} else {
				LOGGER.info("add to order [1]| decrease quantity [2] | back [3]");  
			}

		    String next = getInput();
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
