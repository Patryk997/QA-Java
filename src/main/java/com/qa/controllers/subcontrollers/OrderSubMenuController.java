package com.qa.controllers.subcontrollers;

import java.sql.SQLException;


import org.apache.log4j.Logger;


import com.qa.controllers.MenuController;
import com.qa.controllers.OrdersMenuController;

import com.qa.main.SessionHashMap;

import com.qa.models.Order;
import com.qa.persistence.service.ItemsService;
import com.qa.persistence.service.OrderItemService;
import com.qa.persistence.service.OrderService;
import com.qa.utils.Utils;
import com.qa.views.order.OrderDetailsView;

public class OrderSubMenuController implements SubMenuController<Order> {
	
	public static final Logger LOGGER = Logger.getLogger(OrderSubMenuController.class);
	
	MenuController menu;
	ItemsService itemsService = new ItemsService();
	OrderItemService orderItemService = new OrderItemService();
	OrderService orderService = new OrderService();
	
	public static OrderSubMenuController orderSubMenu;
	
	public static OrderSubMenuController getOrderSubMenu() {
		if(orderSubMenu == null)
			orderSubMenu = new OrderSubMenuController();
		return orderSubMenu;	
	}
	
	public boolean deleteOrderFromSystem(int orderId) {
		int currentOrderId = SessionHashMap.getSessionHashMap().get("orderId");
		if(currentOrderId == orderId) {
			LOGGER.warn("Cannot delete order that has not been finalized yet");
			return false;
		}	
		try {
			orderService.deleteOrder(orderId);
			LOGGER.info("Order deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
		
	@Override
	public Order selectById(int orderId) throws SQLException {
		Order order = orderService.selectOrder(orderId);
		return order;
	}

	@Override
	public String selectSubMenu() {

		Order order = null;
		int orderId = -1;
		boolean flag = true;
		menu = OrdersMenuController.getOrderMenu();
		LOGGER.info("Select Order ID or 0 to return");
		
		while(flag) {
			String select = Utils.getInput();
			if(select == "0") {
				menu.selectMenuOptions();
				flag = false;
				return "back";
			}
			
			try {
				orderId = Utils.convertStringToInt(select);
				order = selectById(orderId);
				flag = false;
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.warn("This item does not exist");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				LOGGER.warn("Select correct product ID");
			}
			
		}
		
		if(order == null) {
			System.out.println("This order doesn not exist. Select order ID from the list");
			menu.selectMenuOptions();	  // return to items list
		} else {
			OrderDetailsView.listOrderDetails(order);
		}
		
		flag = true;
		while(flag) {
			LOGGER.info("delete order from the system [1] | back [2]");
			String next = Utils.getInput();
			switch(next) {
			case "1":
				deleteOrderFromSystem(orderId);	
				
			case "2":	
				flag = false;
				menu.selectMenuOptions();
				break;
			}
		}

		return "query ok";
	}

}
