package com.qa.controllers.subcontrollers;

import java.sql.SQLException;


import org.apache.log4j.Logger;


import com.qa.controllers.MenuController;
import com.qa.controllers.OrdersMenuController;
import com.qa.dto.Order;
import com.qa.security.SessionHashMap;
import com.qa.services.CrudService;
import com.qa.services.ItemsService;
import com.qa.services.OrderService;
import com.qa.services.other.OrderItemService;
import com.qa.utils.Utils;
import com.qa.views.order.OrderDetailsView;

public class OrderSubMenuController implements SubMenuController<Order> {
	
	public static final Logger LOGGER = Logger.getLogger(OrderSubMenuController.class);
	
	MenuController menu;  
	CrudService service;
	OrderItemService orderItemService;
	
	public void setMenu(MenuController menu) { 
		this.menu = menu;
	}
	
	public void setService(CrudService service) {
		this.service = service;
	}
	
	public OrderSubMenuController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public static OrderSubMenuController orderSubMenu;
	
	public static OrderSubMenuController getOrderSubMenu() {
		if(orderSubMenu == null)
			orderSubMenu = new OrderSubMenuController(new OrderItemService()); 
		return orderSubMenu;	
	}
	
	public int getSessionId() {
		return SessionHashMap.getSessionHashMap().get("orderId");
	}
	
	public boolean deleteOrderFromSystem(int orderId) {
		setService(new OrderService());
		int currentOrderId = getSessionId();
		if(currentOrderId == orderId) {
			LOGGER.warn("Cannot delete order that has not been finalized yet");
			return false;
		}	
		try {
			service.delete(orderId);
			LOGGER.info("Order deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
		
	@Override
	public Order selectById(int orderId) throws SQLException {
		setService(new OrderService());
		Object order = service.select(orderId);
		Order order2 = (Order) order;
		return order2;
	}
	
	public String getInput() {
		return Utils.getInput();
	}

	@Override
	public String selectSubMenu() {

		Order order = null;
		int orderId = -1;
		boolean flag = true;
		setMenu(OrdersMenuController.getOrderMenu());
		LOGGER.info("Select Order ID or 0 to return");
		
		while(flag) {
			String select = getInput();
			if(select == "0") {
				flag = false;
				menu.selectMenuOptions();
				return "back";
			}
			
			try {
				orderId = Utils.convertStringToInt(select);
				order = selectById(orderId);
				flag = false;
			} catch (SQLException e) {
				LOGGER.warn("This item does not exist");
			} catch (NumberFormatException e) {
				LOGGER.warn("Select correct order ID");
			}
			
		}
		
		if(order == null) {
			System.out.println("This order doesn not exist. Select order ID from the list");
			flag = false;
			menu.selectMenuOptions();	  // return to items list
		} else {
			OrderDetailsView.listOrderDetails(order);
		}
		
		flag = true;
		while(flag) {
			LOGGER.info("delete order from the system [1] | back [2]");
			String next = getInput();
			switch(next) {
			case "1":
				flag = false;
				deleteOrderFromSystem(orderId);	
				
			case "2":	
				flag = false;
				menu.selectMenuOptions();
				break;
			default:
				LOGGER.warn("Select 1 or 2");
			}
		}

		return "query ok";
	}

}
