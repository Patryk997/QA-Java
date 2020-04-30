package com.qa.security;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.utils.ConnectionMySQL;

public class AuthenticateTest {
	
	Authenticate authenticate;
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
		authenticate = new Authenticate();
	}
	
	@Test
	//@Ignore
	public void login() {
		
		int id = authenticate.login("admin", "admin");
		assertEquals(1, id);
	}
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}
	

}
