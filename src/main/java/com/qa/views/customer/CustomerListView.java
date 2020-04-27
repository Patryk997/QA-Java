package com.qa.views.customer;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.models.Customer;

public class CustomerListView {
	
	public static void listAllCustomers(List<Customer> customers) {
		
		int[] count = {0};
		customers.stream().map(a -> {	
            count[0] = count[0] + 1;

        	System.out.print(a.getId() + " ");
    		System.out.println(a.getName());
    		System.out.println("-------------");

            return a;
        	
        }).collect(Collectors.toList());
		
	}
		

}
