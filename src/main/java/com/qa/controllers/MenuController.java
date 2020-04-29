package com.qa.controllers;

import java.util.List;

public interface MenuController<T> {
 
	String selectMenuOptions();
	List<T> viewAll();
}
