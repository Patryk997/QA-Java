package com.qa.models;

import java.sql.Date;
import java.util.Objects;

public class Order {

	private Integer id;
	private Integer customerId;
	private Date placed;
	private boolean paid;
	private Double total;
	private Customer customer;
	
	public Order() { }
	
	public Order(Integer id) {
		this.id = id;
	}
	
	public Order(Integer id, Customer customer, Date placed, Double total) {
		this.id = id;
		this.customer = customer;
		this.placed = placed;
		this.total = total;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Date getPlaced() {
		return placed;
	}
	public void setPlaced(Date placed) {
		this.placed = placed;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		if (getClass() != obj.getClass())
			return false;
		if(!Objects.equals(id, other.id))
			return false;
		if(!Objects.equals(total, other.total))
			return false;
		if(!Objects.equals(placed, other.placed))
			return false;

		return Objects.equals(customer, other.customer);      
		
	}
	
}
