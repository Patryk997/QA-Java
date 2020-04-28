package com.qa.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.controllers.subcontrollers.ItemSubMenuController;
import com.qa.controllers.subcontrollers.SubMenuController;
import com.qa.models.Item;
import com.qa.persistence.service.ItemsService;
import com.qa.security.Authenticate;
import com.qa.utils.Utils;
import com.qa.views.items.ItemsListView;

public class ItemsMenuController implements MenuController {
	
	public static final Logger LOGGER = Logger.getLogger(ItemsMenuController.class);

	private SubMenuController subMenu;
	ItemsService itemsService;// = new ItemsService();	
	
	public ItemsMenuController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
	
	public static ItemsMenuController itemsMenu;
	public static ItemsMenuController getItemsMenu() {
		if(itemsMenu == null)
			itemsMenu = new ItemsMenuController(new ItemsService());
		return itemsMenu;
	}
	
	@Override
	public void viewAll() {
		/*
		 * lists all items available in the system
		 * */
		try {
			List<Item> items = itemsService.selectAll();
	        ItemsListView.listAllItems(items);
	        subMenu = ItemSubMenuController.getItemsSubMenu();
			subMenu.selectSubMenu(); 

        } catch (Exception e) {
        	e.printStackTrace();
        	selectMenuOptions();
        }
	}
	
	public String add() {
		if(!isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			selectMenuOptions();
		}
			
		boolean flag = true;
		double valueDouble = 0;
		while(flag) { 
			LOGGER.info("Give product name or select 0 to return:");
			String name = getInput();
			if(name.equals("0")) {
				selectMenuOptions();
				flag = false;
			}		
			LOGGER.info("Give value: ");
			String value = getInput();
			
			if(validateName(name)) {
				try {
					valueDouble = convertStringToDouble(value);
				} catch (NumberFormatException e) {
					LOGGER.warn("Must be value format 99.99 or 9.00 and no more than 6 digits long");
				}
				
			} 
			
			if(valueDouble > 0) {
				try {
					Item item = new Item(name, valueDouble);
					itemsService.create(item); 
					LOGGER.info("Product Added");
					flag = false;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	// end of while loop
		selectMenuOptions();
		return "added";
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public boolean isAuthenticated() {
		return Authenticate.isAuthenticated();
	}
	
	public boolean validateName(String name) {
		return Utils.validateName(name);
	}
	
	public double convertStringToDouble(String value) {
		return Utils.convertStringToDouble(value);
	}
	
	@Override
	public String selectMenuOptions() {
        LOGGER.info("*** ITEMS ***");
        if(isAuthenticated())
        	LOGGER.info("view all [1] | back [2] | add to system [3]");
        else
        	LOGGER.info("view all [1] | back [2]");
        String next = "";
        MainController menu = MainController.getMainMenu();
        boolean flag = true;
        while(flag) {	
        	next = getInput();
        	switch(next) {
				case "1":
					viewAll();
					flag = false;
					break;
				case "2":
					menu.selectMenuOptions();
					flag = false;
					break;
				case "3":		
					add();
					flag = false;
					break;
			    default:
			    	LOGGER.warn(" -- Wrong command - select 1, 2 or 3 ");		
        	}
        } // end of while loop
        return "selected";
	}
	

}
