package com.qa.views.items;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.dto.Item;


public class ItemsListView {

	public static void listAllItems(List<Item> items) {
		
		int[] count = {0};
		items.stream().map(a -> {	
            //count[0] = count[0] + 1; 

        	System.out.print("-- " + a.getId()); 
        	System.out.print(" | " + a.getName());
        	System.out.println(" | " + a.getValue() + " | ");
        	System.out.println("----------------------");
        	//if(count[0] >= 3) {
        	//	System.out.println(a.getValue() + " | ");
        	//	System.out.println("----------------------");
        	//} else {
        		
        //}
            return a;
        	
        }).collect(Collectors.toList());
		
	}
}
