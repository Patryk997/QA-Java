package com.qa.views.order;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.qa.dto.Order;

public class OrdersListView {
	
	public static final Logger LOGGER = Logger.getLogger(OrdersListView.class);

	public static void listAllOrders(List<Order> ordersList) {
        System.out.println("  ID |   name   | placed |   paid    |");
        System.out.println("---------------------------------------");
        System.out.println();
		ordersList.stream().map(a -> { 
			
			System.out.print("-- " + a.getId() + " | ");
			System.out.print(" | " + a.getCustomer().getName() + " | ");
			System.out.print(a.getPlaced() + " | ");
			System.out.println(a.getTotal() + " | ");
	
			return a;
			
		}).collect(Collectors.toList()); 
	}

}
