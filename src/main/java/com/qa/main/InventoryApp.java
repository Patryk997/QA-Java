package com.qa.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controllers.MainController;
import com.qa.dto.Customer;
import com.qa.dto.Order;
import com.qa.security.SessionHashMap;
import com.qa.services.CustomerService;
import com.qa.services.OrderService;
import com.qa.utils.Utils;

public class InventoryApp { 

	public static final Logger LOGGER = Logger.getLogger(InventoryApp.class);
	
	private CustomerService customerService;
	private OrderService orderService;
	private Customer customer;
	private MainController mainController;
	
	public void setCustomerService(CustomerService customerServie) {
		this.customerService = customerServie;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public SessionHashMap getSessionHashMap() {
		return SessionHashMap.getSessionHashMap();
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public void runInventory () {  
		String name = "";
		boolean flag = true;
		SessionHashMap sessionHashMap = getSessionHashMap();
		
		while(flag) {
			LOGGER.info("Set customer name [1] or skip and select later [2]");
			String option = getInput();
			switch(option) {
				case "1":
					LOGGER.info("select customer name");
					name = getInput();
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
				
	
		setCustomer(new Customer(name));
		setCustomerService(new CustomerService());
		setOrderService(new OrderService());
		
		int customerInserted = 0;
		int newOrderId = 0;
		
		try {
			customerInserted = customerService.create(customer);
		} catch (SQLException e) {
			LOGGER.info("A problem occured. try again later");
		}

		try {
			Order order = new Order();
			order.setCustomerId(customerInserted);
			newOrderId = orderService.create(order);
		} catch (SQLException e) {
			LOGGER.info("A problem occured. try again later");
		}
		//System.out.println("successfully inserted a customer name ");
		
		if(customerInserted > 0 && newOrderId > 0) {
			
			sessionHashMap.put("customerId", customerInserted);
			sessionHashMap.put("orderId", newOrderId);
			sessionHashMap.put("authenticated", 0);
			setMainController(MainController.getMainMenu());
			mainController.selectMenuOptions();
		}
	}
}
