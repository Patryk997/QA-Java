package com.qa.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.main.ScannerHashMap;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
	
	
	
	//@Spy // for the methods in customerController
	//@InjectMocks // for any classes our customerController calls (in this case customerService)
	//public MainController mainController;
	
	@Test
	public void selectMenuOptions() {

		MainController mainController = new MainController();
		MainController mainController2 = Mockito.spy(mainController);
		ScannerHashMap map = ScannerHashMap.getScannerHashMap();
		ScannerHashMap map2 = Mockito.spy(map);
		String id = "1";
		Mockito.doReturn(id).when(mainController2).getInput();
		Mockito.doReturn(true).when(mainController2).getScannerMap(map, "1");
		assertEquals("1", mainController2.selectMenuOptions());
	}
	
	@Test
	public void testMock() {
		
		//MainController mainController1 = mock(MainController.class);
		//MainController mainController1 = new MainController();
		MainController mainController2 = Mockito.spy(new MainController());
		String id = "1";
		Mockito.doReturn(id).when(mainController2).getInput();
		//when(mainController2.getInput()).thenReturn(id);
		assertEquals("1", mainController2.testMock());
	}

}
