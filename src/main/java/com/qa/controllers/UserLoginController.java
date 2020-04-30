package com.qa.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.dto.Customer;
import com.qa.security.Authenticate;
import com.qa.security.SessionHashMap;
import com.qa.services.CustomerService;
import com.qa.utils.Utils;

public class UserLoginController implements MenuController<Customer> {
	
	MainController menu;
	
	public static final Logger LOGGER = Logger.getLogger(UserLoginController.class);
	
    private static UserLoginController adminMenuController;
	public static UserLoginController getAdminLogin() {
		if(adminMenuController == null)
			adminMenuController = new UserLoginController(MainController.getMainMenu());
		return adminMenuController;
	}
	
	public UserLoginController(MainController menu) {
		this.menu = menu;
	}
	
	
	
	boolean isAuthenticated() {
		return Authenticate.isAuthenticated();
	}
	 
	String getInput() {
		return Utils.getInput();
	}
	
	Authenticate getAuthenticate() { 
		return Authenticate.getAuthenticate();
	}
	
	public String login() {
		//MainController menu = MainController.getMainMenu();
		boolean flag = true;
		
		if(isAuthenticated()) {
			logout();
			menu.selectMenuOptions();
			flag = false;
			return "logout";
		} 
		
		
		while(flag) { 
			LOGGER.info("Your username or 0 to return:");
			String username = getInput();
			if(username.equals("0")) {
				menu.selectMenuOptions();
				flag = false;
				return "main";
			}
			LOGGER.info("Your password:");
			String password = getInput();
			
			Authenticate authenticate = getAuthenticate(); 
			int userId = authenticate.login(username, password);
			if( userId > 0) {
				LOGGER.info("** Successfully authenticated as admin **");
				flag = false;
	            SessionHashMap.getSessionHashMap().put("authenticated", 1000);
	            updateCustomerAdmin(userId);
	            menu.selectMenuOptions();
			} else {
				LOGGER.info("The username and password you entered are incorrect.");
			}

		}	// end of while loop
		
		return "loggedIn";
	}
	
	public String updateCustomerAdmin(int userId) {
		int customerId = SessionHashMap.getSessionHashMap().get("customerId");
		CustomerService customerService = new CustomerService();
		Customer customer = new Customer(customerId, userId);
		try {
			customerService.updateCustomerAsAdmin(customer);
		} catch (SQLException e) {
			LOGGER.info("Problem occured, try again later");
		}
		return "customer updated";
		
	}
	
	public String logout() {
		Authenticate authenticate = Authenticate.getAuthenticate();
		authenticate.logout();
		return "logout";
	}

	@Override
	public String selectMenuOptions() {
		LOGGER.info("*** WELCOME TO ADMIN LOGIN ***");
        
        String next = "";
        boolean flag = true;
        while(flag) {
        	LOGGER.info("Login/Logout as admin [1] | back [2]");
        	next = getInput();
        	switch(next) {
				case "1":
					login();
					flag = false;
					break;
				
				case "2":		
					menu.selectMenuOptions();
					flag = false;
					break;
					
			    default:
			    	LOGGER.info(" -- Wrong command - select 1, 2 or 3 ");		
        	}
        } // end of while loop	
        return "selected";
	}

	@Override
	public List<Customer> viewAll() {
		// To further implementation - list All users
		return null;
	}

}
