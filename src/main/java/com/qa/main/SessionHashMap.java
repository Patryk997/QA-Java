package com.qa.main;

import java.util.HashMap;

public class SessionHashMap extends HashMap<String, Integer> {
	/*
	 this class imitates a session where customerId and orderId 
	 once created on startUp are stored. No need to query database,
	 just retrieve from HashMap. It's singleton bc only one instance
	 needed for all the user session. It will have 2 keys:
	 custmerId
	 orderId
	 * 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SessionHashMap sessionHashMap;
	
	public static SessionHashMap getSessionHashMap() {
		if(sessionHashMap == null)
			sessionHashMap = new SessionHashMap();
		return sessionHashMap;
	}

}
