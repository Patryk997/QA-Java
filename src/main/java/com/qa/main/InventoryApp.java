package com.qa.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controllers.MainController;
import com.qa.models.Customer;
import com.qa.models.Order;
import com.qa.persistence.service.CustomerService;
import com.qa.persistence.service.OrderService;
import com.qa.utils.Utils;

public class InventoryApp {

	public static final Logger LOGGER = Logger.getLogger(InventoryApp.class);
	
	public void runInventory () {
		String name = "";
		boolean flag = true;
		SessionHashMap sessionHashMap = SessionHashMap.getSessionHashMap();
		
		while(flag) {
			LOGGER.info("Set customer name [1] or skip and select later [2]");
			String option = Utils.getInput();
			switch(option) {
				case "1":
					LOGGER.info("select customer name");
					name = Utils.getInput();
					sessionHashMap.put("default", 0);
					flag = false;
					break;
				case "2":
					name = "default";
					sessionHashMap.put("default", 1);
					flag = false;
					break;
				default:
					LOGGER.info("You did not choose any option.");	
			}
		}
				
		CustomerService customerService = new CustomerService();
		OrderService orderService = new OrderService();
		
		Customer customer = new Customer(name);
		int customerInserted = 0;
		int newOrderId = 0;
		
		try {
			customerInserted = customerService.insertCustomer(customer);
		} catch (SQLException e) {
			LOGGER.info("A problem occured. try again later");
		}

		try {
			Order order = new Order();
			order.setCustomerId(customerInserted);
			newOrderId = orderService.createOrder(order);
		} catch (SQLException e) {
			LOGGER.info("A problem occured. try again later");
		}
		//System.out.println("successfully inserted a customer name ");
		
		if(customerInserted > 0 && newOrderId > 0) {
			
			sessionHashMap.put("customerId", customerInserted);
			sessionHashMap.put("orderId", newOrderId);
			sessionHashMap.put("authenticated", 0);
			MainController menu = MainController.getMainMenu();
			menu.selectMenuOptions();
		}
	}
}
