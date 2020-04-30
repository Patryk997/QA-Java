package com.qa.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.utils.ConnectionMySQL;
import com.qa.utils.ScannerHashMap;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
	}

	@Test
	//@Ignore
	public void selectMenuOptions() {
		
		ConnectionMySQL.setTestable(true);
		

		MainController mainController = new MainController();
		MainController mainController2 = Mockito.spy(mainController);
		ScannerHashMap map = ScannerHashMap.getScannerHashMap();
		ScannerHashMap map2 = Mockito.spy(map);
		String id = "1";
		Mockito.doReturn(id).when(mainController2).getInput();
		Mockito.doReturn(true).when(mainController2).getScannerMap(map, "1");
		assertEquals("1", mainController2.selectMenuOptions());
	}
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}
	


}
