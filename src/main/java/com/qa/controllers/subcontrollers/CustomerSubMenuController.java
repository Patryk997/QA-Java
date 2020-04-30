package com.qa.controllers.subcontrollers;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controllers.CustomersMenuController;
import com.qa.controllers.MenuController;
import com.qa.dto.Customer;
import com.qa.security.SessionHashMap;
import com.qa.services.CrudService;
import com.qa.services.CustomerService;
import com.qa.services.other.OrderItemService;
import com.qa.utils.Utils;

public class CustomerSubMenuController implements SubMenuController<Customer> {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerSubMenuController.class);
	
	MenuController menu;
	CrudService service; 
	OrderItemService orderItemService;
	
	public void setMenu(MenuController menu) {  
		this.menu = menu;
	}
	
	public void setService(CrudService service) {
		this.service = service;
	}
	
	public CustomerSubMenuController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
	
	public static CustomerSubMenuController customerSubMenu;
	
	

	public static CustomerSubMenuController getCustomerSubMenu() {
		if(customerSubMenu == null)
			customerSubMenu = new CustomerSubMenuController(new OrderItemService());
		return customerSubMenu;
	}
	 
	
	
	@Override
	public Customer selectById(int id) throws SQLException {
		setService(new CustomerService());
		Object customer = service.select(id);
		Customer customer2 = (Customer) customer;
		return customer2;
	}
	
	public void deleteCustomerFromSystem(int id) {
		int customerId = getCustomerId();
		//menu = CustomersMenuController.getCustomerMenu();
		if(id == customerId) {
			LOGGER.info("** You cannot delete current customer **");
		} else {
			try {
				service.delete(id);
				LOGGER.info("Customer deleted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCustomerId() {
		return SessionHashMap.getSessionHashMap().get("customerId");
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public CustomersMenuController getCustomerMenu() {
		return CustomersMenuController.getCustomerMenu();
	}
	
	public int convertStringToInt(String select) {
		return Utils.convertStringToInt(select);
	}

	@Override
	public String selectSubMenu() {
		setMenu(CustomersMenuController.getCustomerMenu());
		Customer customer = null;
		boolean flag = true;
		int customerId = 0;
		//menu = getCustomerMenu();
		LOGGER.info("Select customer ID or 0 to return");
		while(flag) {
			String select = getInput();
			if(select == "0") {
			    flag = false;
				menu.selectMenuOptions();
			}
				
			try {
				customerId = convertStringToInt(select);
				customer = selectById(customerId);
				flag = false;
			} catch (SQLException e) {
				LOGGER.warn("This customer does not exist");
				
			} catch (NumberFormatException e) {
				LOGGER.warn("Select correct customer ID");
				
			}
		}	
		if(customer == null) {
			LOGGER.warn("This customer doesn not exist. Select customer ID from the list");
			menu.selectMenuOptions();	  // return to items list
		} else {
			LOGGER.info(customer.getId() + " " + customer.getName());
		}
		flag = true;
		while(flag) {
			LOGGER.info("delete customer [1]  | back [2]");
			String next = getInput();
			switch(next) {
				case "1":
					flag = false;
					deleteCustomerFromSystem(customerId);	
					break;
					
				case "2":
					flag = false;
					menu.selectMenuOptions();
					break;

				default:	
					LOGGER.warn(" -- Wrong command -- ");
			}
		}
		menu.selectMenuOptions();

		return "query succeded";
		
	}

	

}
