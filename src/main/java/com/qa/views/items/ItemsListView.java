package com.qa.views.items;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.models.Item;


public class ItemsListView {

	public static void listAllItems(List<Item> items) {
		
		int[] count = {0};
		items.stream().map(a -> {	
            count[0] = count[0] + 1;

        	System.out.print(a.getId() + " ");
        	System.out.print(a.getName() + " ");
        	
        	if(count[0] >= 3) {
        		System.out.println(a.getValue() + " | ");
        		System.out.println("----------------------");
        	} else {
        		System.out.print(a.getValue() + " | ");
        	}
            return a;
        	
        }).collect(Collectors.toList());
		
	}
}
