package com.qa.dto;

import java.util.Objects;

public class OrderItem {
	/* 
	 this class works like middle man between Order and Item entity
	 as the relation Order-Item is M2M
	*/
	
	private Integer orderItemId;
	private Integer itemId;
	private Integer orderId;
	private Integer quantity; 
	private Item item;
	
	public OrderItem(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public OrderItem(Integer itemId, Integer orderId) {
		this.itemId = itemId;
		this.orderId = orderId;
	}
	
	
	
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if (!(obj instanceof OrderItem)) {
			return false;
		}
		OrderItem other = (OrderItem) obj;
		if (getClass() != obj.getClass())
			return false;
		if(!Objects.equals(item, other.item))
			return false;
		if(!Objects.equals(quantity, other.quantity))
			return false;
		if(!Objects.equals(itemId, other.itemId))
			return false;

		return Objects.equals(orderId, other.orderId);      
		
	}
	

}
