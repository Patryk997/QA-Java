package com.qa.views.orderItem;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.dto.OrderItem;
import com.qa.utils.Utils;


public class OrderItemListView {
	
	public static double listOrderItems(List<OrderItem> orderItems) {
		
		int[] count = {0};
		double[] total = {0};
		System.out.println("--------------------");
		orderItems.stream().map(a -> {	
            count[0] = count[0] + 1;
            total[0] = total[0] + ((a.getItem().getValue()) * (double) a.getQuantity());
        	System.out.print(" --- " + a.getItem().getId() + " | " + a.getItem().getName()+ " | ");
        	System.out.print("quantity: " + a.getQuantity());
        	System.out.println();
        	
        	    	
            return a;
        	
        }).collect(Collectors.toList());
		
		System.out.println(" --- ORDER TOTAL: --- " + Utils.formatDouble(total[0]) + " GBP");
		return total[0];
	}

}
