package com.qa.main;

import java.util.HashMap;
import java.util.Map;

import com.qa.controllers.UserLoginController;
import com.qa.controllers.CustomersMenuController;
import com.qa.controllers.ItemsMenuController;
import com.qa.controllers.MenuController;
import com.qa.controllers.OrdersMenuController;


public class ScannerHashMap  {
	
	private Map<String, MenuController> scannerMap;
	
	public Map<String, MenuController> getScannerMap(){
		return scannerMap;
	}

	public static ScannerHashMap scannerHashMap;
	
	public static ScannerHashMap getScannerHashMap() {
		if(scannerHashMap == null)
			scannerHashMap = new ScannerHashMap();
		return scannerHashMap;
	}

	public ScannerHashMap() {
		scannerMap = new HashMap<String, MenuController>();
		scannerMap.put("1", ItemsMenuController.getItemsMenu());
		scannerMap.put("2", CustomersMenuController.getCustomerMenu() );
		scannerMap.put("3", OrdersMenuController.getOrderMenu());
		scannerMap.put("4", UserLoginController.getAdminLogin());
		//super.put("exit", ExitStrategyImp.returnStrategy());
	}
}
