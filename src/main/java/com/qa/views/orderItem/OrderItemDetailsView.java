package com.qa.views.orderItem;

import com.qa.dto.Item;
import com.qa.dto.OrderItem;

public class OrderItemDetailsView {
	
	public static void orderItemDetails(Item item) {
		
		System.out.println(item.getName());
		System.out.println(item.getValue());
	}

}
