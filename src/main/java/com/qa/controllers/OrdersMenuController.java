package com.qa.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.controllers.subcontrollers.OrderSubMenuController;
import com.qa.controllers.subcontrollers.SubMenuController;
import com.qa.main.SessionHashMap;
import com.qa.models.Item;
import com.qa.models.Order;
import com.qa.models.OrderItem;
import com.qa.persistence.service.OrderItemService;
import com.qa.persistence.service.OrderService;
import com.qa.security.Authenticate;
import com.qa.utils.Utils;
import com.qa.views.order.OrdersListView;
import com.qa.views.orderItem.OrderItemDetailsView;
import com.qa.views.orderItem.OrderItemListView;

public class OrdersMenuController implements MenuController {
	
	public static final Logger LOGGER = Logger.getLogger(OrdersMenuController.class);

	private MenuController menu;
	private SubMenuController subMenu;
	OrderItemService orderItemService;// = new OrderItemService();
	OrderService orderService;// = new OrderService();
	
    public static OrdersMenuController orderMenu;
    
    OrdersMenuController(OrderItemService orderItemService, OrderService orderServide) {
    	this.orderItemService = orderItemService;
    	this.orderService = orderServide;
    }
	
	public static OrdersMenuController getOrderMenu() {
		if(orderMenu == null)
			orderMenu = new OrdersMenuController(new OrderItemService(), new OrderService());
		return orderMenu;
	}
	
	@Override
	public void viewAll() {
		/*
		 * lists all items available in the system
		 * */
		subMenu = OrderSubMenuController.getOrderSubMenu();
		if(!Authenticate.isAuthenticated()) {
			LOGGER.info("** Not Authorized **");
			getOrderMenu().selectMenuOptions();
		}
		
		try {
			List<Order> ordersList = orderService.selectAllOrders();
	        OrdersListView.listAllOrders(ordersList);
			subMenu.selectSubMenu(); 

        } catch (Exception e) {
        	e.printStackTrace();
        	menu.selectMenuOptions();
        }
	}
	
	public void delete(int itemId, int orderId) {
		try {
			int executed = orderItemService.delete(itemId, orderId);
			if(executed > 0) {
				LOGGER.info("Item deleted from the order");	
				menu.selectMenuOptions();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String completeOrder(boolean paid, int orderId) {
		int defaultUser = getDefault();
		if(defaultUser > 0) {
			LOGGER.info("*** First update customer name to complete the order ***");
			menu = CustomersMenuController.getCustomerMenu();
			menu.selectMenuOptions();
			return "update customer";
		}
		int customerId = getCustomerId();
		try {
			orderService.completeOrder(paid, orderId);
			Order order = new Order();
			order.setCustomerId(customerId);
			int newOrderId = orderService.createOrder(order);
			putNewOrderId(newOrderId);
			LOGGER.info("*** Your order has been completed ***");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		selectMenuOptions();
		return "completed";
	}
	
	public double view(int orderId) {	
		/*
		 * first list all items in the order 
		 * then update on product ID selected
		 * * */	
		double total = 0;
		try {
			List<OrderItem> orderItems = orderItemService.listOrderItems(orderId);
	        total = OrderItemListView.listOrderItems(orderItems);
            setTotal(total, orderId);
	        if(!orderItems.isEmpty())
	        	update();
        } catch (SQLException e) {
        	e.printStackTrace();
        	menu.selectMenuOptions();
        }
		return total;
	}
	
	public boolean setTotal(double total, int orderId) {
		return orderService.setTotal(total, orderId); 
	}
	
	public int getOrderId() {
		return SessionHashMap.getSessionHashMap().get("orderId");
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public int convertStringToInt(String select) {
		return Utils.convertStringToInt(select);
	}
	
	public String orderItemDetailView(Item item) {
		OrderItemDetailsView.orderItemDetails(item);
		return "view";
	}
	
	public boolean isAuthenticated() {
		return Authenticate.isAuthenticated();
	}
	
	public int getDefault() {
		return SessionHashMap.getSessionHashMap().get("default");
	}
	
	public int getCustomerId() {
		return SessionHashMap.getSessionHashMap().get("customerId");
	}
	
	public void putNewOrderId(int newOrderId) {
		SessionHashMap.getSessionHashMap().put("orderId", newOrderId);
	}
	
	public String update() {
		int orderId = getOrderId();
		boolean flag = true;
		int itemId = -1;
		Item item = null;
		LOGGER.info("Select ID product | complete the order [C] | return [R]");
		while(flag) {
			String select = getInput();
			if(select.equalsIgnoreCase("R")) {
				selectMenuOptions();
				flag = false;
				return "return";
			} else if (select.equalsIgnoreCase("C")) {
				completeOrder(true, orderId);
				flag = false;
				return "complete";
			} else {
				try {
					itemId = Utils.convertStringToInt(select); // exception if we insert a String instead a number
					item = orderItemService.selectOrderItem(itemId, orderId);
					flag = false;
		        } catch (NumberFormatException e ) {
		        	LOGGER.warn("select correct product ID");   
		        	flag = false;
		        	//menu.selectMenuOptions();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        	flag = false;
		        }
			}

		} // end of while loop
		
		if(item == null) {
			LOGGER.warn("This item doesn not exist. Select ID from the list");
			view(orderId);	  // return to items list
		} else {
			//OrderItemDetailsView.orderItemDetails(item);
			orderItemDetailView(item);
		
		}
		
		flag = true;
		while(flag) { 
			LOGGER.info("delete from basket [1] | back [2]");  
		    String next = getInput();
			switch(next) {
				case "1":
					delete(itemId, orderId);	
					flag = false;
					break;
									
				case "2":	
					flag = false;
					selectMenuOptions();
					break;
					
				default:	
					LOGGER.warn(" -- Wrong command -- ");
			}
		} // end of while loop
		return "updated";
	}
	
	@Override
	public String selectMenuOptions() {
		LOGGER.info("*** ORDERS ***");
		if(isAuthenticated())
			LOGGER.info("view [1] | back [2] | view all [3] ");
        else
        	LOGGER.info("view [1] | back [2]");
		
		
		String next = "";
		MainController menu = MainController.getMainMenu();
		int orderId = getOrderId();
	
		boolean flag = true;
        while(flag) {
        	
        	next = getInput();
        	switch(next) {
				case "1":
					view(orderId);
					flag = false;
					break;

				case "2":
					menu.selectMenuOptions();
					flag = false;
					break;
					
				case "3":
					viewAll();
					flag = false;
					break;
									
			    default:
			    	LOGGER.warn(" -- Wrong command - select 1, 2 or 3 ");		
        	}
        } // end of while loop
        return "selected";
	}

}
