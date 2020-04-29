package com.qa.security;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AuthenticateTest {
	
	Authenticate authenticate;
	
	@Before
	public void setUp() {
		authenticate = new Authenticate();
	}
	
	@Test
	public void login() {
		
		int id = authenticate.login("admin", "admin");
		assertEquals(1, id);
	}
	

}
