package com.qa.views.orderItem;

import com.qa.models.Item;

public class OrderItemDetailsView {
	
	public static void orderItemDetails(Item item) {
		
		System.out.println(item.getName());
		System.out.println(item.getValue());
	}

}
