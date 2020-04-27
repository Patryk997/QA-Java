package com.qa.main;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controllers.MainController;
import com.qa.models.Customer;
import com.qa.models.Order;
import com.qa.persistence.service.CustomerService;
import com.qa.persistence.service.OrderService;
import com.qa.utils.Utils;

public class InventoryRunner {
	

	public static void main(String[] args) throws IOException {
		
	    InventoryApp app = new InventoryApp();
	    app.runInventory();
		
	}

}
