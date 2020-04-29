package com.qa.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;

import com.qa.controllers.subcontrollers.CustomerSubMenuController;
import com.qa.controllers.subcontrollers.SubMenuController;
import com.qa.main.SessionHashMap;
import com.qa.models.Customer;
import com.qa.persistence.service.Service;
import com.qa.persistence.service.CustomerService;
import com.qa.security.Authenticate;
import com.qa.utils.Utils;
import com.qa.views.customer.CustomerListView;

public class CustomersMenuController implements MenuController {
	 
	public static final Logger LOGGER = Logger.getLogger(CustomersMenuController.class);

	private MenuController menu;
	private SubMenuController subMenu;
	private Service service;
	
	public void setMenu(MenuController menu) {
		this.menu = menu;
	}
	
	
	public void setSubMenu(SubMenuController subMenu) {
		this.subMenu = subMenu;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	//CustomerService customerService;// = new CustomerService();
	
    public static CustomersMenuController customerMenu;
    
    public CustomersMenuController(Service service) {
    	this.service = service;
	}
    
    public CustomersMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public static CustomersMenuController getCustomerMenu() {
		if(customerMenu == null)
			customerMenu = new CustomersMenuController(new CustomerService());
		return customerMenu;
	}
	
	@Override
	public List<Customer> viewAll() {
		/*
		 * lists all items available in the system
		 * */
		List<Customer> customers = new ArrayList<Customer>();
		setMenu(CustomersMenuController.getCustomerMenu());
		setSubMenu(CustomerSubMenuController.getCustomerSubMenu());
		if(!isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			menu.selectMenuOptions();
		} else {
			try {
				customers = service.selectAll();
				CustomerListView.listAllCustomers(customers);
				subMenu.selectSubMenu();
	
			} catch (SQLException e) {
				e.printStackTrace();
				menu.selectMenuOptions();
			}
		}
		return customers;
	}
	
	int getCustomerId() {
		return SessionHashMap.getSessionHashMap().get("customerId");
	}
	

	
	public String update() { 
		setMenu(CustomersMenuController.getCustomerMenu());
		setService(new CustomerService());
		boolean flag = true;
		String name = "";
		int customerId = getCustomerId();
		while(flag) {
			LOGGER.info("Give a new name: | or select 'back' to return ");
			name = getInput();
			if(name.equals("back")) {
				flag = false;
				menu.selectMenuOptions();
			} else {
				if(!name.equals("")) {
					Customer customer = new Customer(customerId, name);
					try {
						service.update(customer);
						flag = false;
						LOGGER.info("** Successfully updated customer name **");
						SessionHashMap.getSessionHashMap().replace("default", 0);
						menu.selectMenuOptions();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return "updated";
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public boolean isAuthenticated() {
		return Authenticate.isAuthenticated();
	}
	
	
	@Override
	public String selectMenuOptions() {
		LOGGER.info("*** CUSTOMERS ***");
		if(isAuthenticated())
			LOGGER.info("update [1] | back [2]| view all [3]");
		else
			LOGGER.info("update [1] | back [2]");
			
		String next = "";
		MainController menu = MainController.getMainMenu();
		boolean flag = true;
		while(flag) {	
			next = getInput();
			switch(next) {
			case "1":
				update();
				flag = false;
				break;
			case "2":		
				menu.selectMenuOptions();
				flag = false;
				break;
			case "3":	
				flag = false;
				viewAll();
				break;			
		    default:
		    	LOGGER.warn(" -- Wrong command - select 1, 2 or 3 ");		
			}
		}
		return "selected";

	}

}
