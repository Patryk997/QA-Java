package com.qa.views.order;

import com.qa.dto.Order;

public class OrderDetailsView {

	public static void listOrderDetails(Order order) {

		System.out.println(order.getCustomer().getName());
		System.out.println(order.getPlaced());
		System.out.println(order.getTotal());
	}

}
