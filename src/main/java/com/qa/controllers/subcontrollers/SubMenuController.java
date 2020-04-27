package com.qa.controllers.subcontrollers;

import java.sql.SQLException;

public interface SubMenuController<T> {

	String selectSubMenu();
	
	T selectById(int id) throws SQLException;
}
