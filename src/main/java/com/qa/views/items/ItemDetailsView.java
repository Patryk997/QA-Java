package com.qa.views.items;

import com.qa.dto.Item;

public class ItemDetailsView {

	public static void listItemDetails(Item item){
		
		System.out.println(" -- " + item.getName());
		System.out.println(" -- " + item.getValue());
	}
}
