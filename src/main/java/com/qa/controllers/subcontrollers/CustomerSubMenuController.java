package com.qa.controllers.subcontrollers;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controllers.CustomersMenuController;
import com.qa.controllers.MenuController;
import com.qa.main.SessionHashMap;
import com.qa.models.Customer;
import com.qa.persistence.service.CustomerService;
import com.qa.utils.Utils;

public class CustomerSubMenuController implements SubMenuController<Customer> {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerSubMenuController.class);
	
	MenuController menu;
	
	public static CustomerSubMenuController customerSubMenu;
	CustomerService customerService = new CustomerService();

	public static CustomerSubMenuController getCustomerSubMenu() {
		if(customerSubMenu == null)
			customerSubMenu = new CustomerSubMenuController();
		return customerSubMenu;
	}
	
	@Override
	public Customer selectById(int id) throws SQLException {
		Customer customer = customerService.selectCustomer(id);
		return customer;
	}
	
	public void deleteCustomerFromSystem(int id) {
		int customerId = SessionHashMap.getSessionHashMap().get("customerId");
		menu = CustomersMenuController.getCustomerMenu();
		if(id == customerId) {
			LOGGER.info("** You cannot delete current customer **");
		} else {
			try {
				customerService.deleteCustomer(id);
				LOGGER.info("Customer deleted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String selectSubMenu() {
		
		Customer customer = null;
		boolean flag = true;
		int customerId = 0;
		menu = CustomersMenuController.getCustomerMenu();
		LOGGER.info("Select customer ID or 0 to return");
		while(flag) {
			String select = Utils.getInput();
			if(select == "0") {
				menu.selectMenuOptions();
				flag = false;
			}
				
			try {
				customerId = Utils.convertStringToInt(select);
				customer = selectById(customerId);
				flag = false;
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.warn("This customer does not exist");
			} catch (NumberFormatException e) {
				e.printStackTrace();
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
			String next = Utils.getInput();
			switch(next) {
				case "1":
					deleteCustomerFromSystem(customerId);			
					break;
					
				case "2":
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
