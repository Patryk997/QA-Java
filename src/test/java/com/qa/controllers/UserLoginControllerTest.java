package com.qa.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.utils.ConnectionMySQL;

public class UserLoginControllerTest {
	
	UserLoginController userLoginController;
	MainController mainController;
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
	}
	
	@Test
	public void login() {

		mainController = mock(MainController.class);
		userLoginController = new UserLoginController(mainController);
		
		UserLoginController userLoginController2 = Mockito.spy(userLoginController);
		
		Mockito.doReturn(false).when(userLoginController2).isAuthenticated();
		Mockito.doReturn("admin").doReturn("admin").when(userLoginController2).getInput();
		Mockito.doReturn("admin").when(userLoginController2).updateCustomerAdmin(anyInt());
		Mockito.doReturn("ok").when(mainController).selectMenuOptions();
		
		assertEquals("loggedIn", userLoginController2.login());
	}
	
	@Test
	public void selectMenuOptions() {
		mainController = mock(MainController.class);
		userLoginController = new UserLoginController(mainController);
		
		UserLoginController userLoginController2 = Mockito.spy(userLoginController);
		
		Mockito.doReturn("1").when(userLoginController2).getInput();
		Mockito.doReturn("ok").when(userLoginController2).login();
		
		assertEquals("selected", userLoginController2.selectMenuOptions());
		
		Mockito.doReturn("2").when(userLoginController2).getInput();
		Mockito.doReturn("ok").when(mainController).selectMenuOptions();
		
		assertEquals("selected", userLoginController2.selectMenuOptions());
		
		
	}
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}

}
