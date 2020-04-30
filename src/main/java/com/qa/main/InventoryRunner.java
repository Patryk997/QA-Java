package com.qa.main;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controllers.MainController;
import com.qa.dto.Customer;
import com.qa.dto.Order;
import com.qa.services.CustomerService;
import com.qa.services.OrderService;
import com.qa.utils.Utils;

public class InventoryRunner {
	

	public static void main(String[] args) throws IOException {
		
	    InventoryApp app = new InventoryApp();
	    app.runInventory();
		
	}

}
