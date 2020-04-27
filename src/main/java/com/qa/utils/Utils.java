package com.qa.utils;

import java.util.Scanner;

public class Utils {
	
	public static final Scanner SCANNER = new Scanner(System.in);

	public Utils() {
           
	}

	public static String getInput() {
		return SCANNER.nextLine();
	}
	
	public static String formatDouble(double total) {
		String numberAsString = String.format ("%.2f", total);
		return numberAsString;
	}

	public static int convertStringToInt(String select) {
		int intFromString  = Integer.valueOf(select);
		return intFromString;
	}
	
	public static boolean validateName(String name) {
		if(name.length() < 7) {
			System.out.println("Minimum 7 characters name");
			return false;
		}
		
		return true;
	}
	
	public static double convertStringToDouble(String value) throws NumberFormatException {
		double doubleValue = 0;
		if(!(value.contains(".") && value.length() < 9)) {
			throw new NumberFormatException();
		}
		doubleValue = Double.valueOf(value);
		return doubleValue;
	}
	


}
