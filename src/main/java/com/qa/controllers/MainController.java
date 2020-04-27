package com.qa.controllers;

import org.apache.log4j.Logger;

import com.qa.main.ScannerHashMap;
import com.qa.utils.Utils;

public class MainController {
	
	public static final Logger LOGGER = Logger.getLogger(MainController.class);

	ScannerHashMap scannerMap;
	boolean exit = false;
	public static MainController menuMain;
	MenuController menu;
	
	public static MainController getMainMenu() {
		if(menuMain == null) {
			menuMain = new MainController();
		}
		return menuMain;
	}
	
	public String getInput() {
		return Utils.getInput().toLowerCase();
	}
	
	public boolean getScannerMap(ScannerHashMap map, String option) {
		if(scannerMap.getScannerMap().containsKey(option)) {
			menu = scannerMap.getScannerMap().get(option);
			menu.selectMenuOptions();
			return true;
		}
		//return scannerMap.getScannerMap().containsKey(option); 
		return false;
	}
    
	public String selectMenuOptions() {
        boolean flag = true;
        scannerMap = ScannerHashMap.getScannerHashMap();
        String option = "";
        System.out.println("pizdaa");
	    while(flag) {
			LOGGER.info("Choose: items [1] customers [2] orders [3] admin [4]");
			option = getInput();
			
			if (option.equals("exit")) {
				exit = true;
				return "return";
			}
			
			if(getScannerMap(scannerMap, option)) {
				//menu = scannerMap.getScannerMap().get(option);
				flag = false;
				//menu.selectMenuOptions();
			} else {
	        	LOGGER.warn("Wrong option");
	      	
	        }
	                
//	        if(scannerMap.getScannerMap().containsKey(option)) {
//			   menu = scannerMap.getScannerMap().get(option);
//			   flag = false;
//			   menu.selectMenuOptions();
//	        } else {
//	        	LOGGER.warn("Wrong option");
//	        }
        } // end of while loop
	    return option;

	}
	
	public String testMock() {
		String option = getInput();
		return option;
	}
}
