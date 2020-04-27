package com.qa.models;

import java.util.Objects;

public class Customer {
	
	Integer id;
	String name;
	Integer userId;
	
	public Customer(String name) {
		this.name = name;
	}
	
	public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Customer(Integer id, Integer userId) {
		this.id = id;
		this.userId = userId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		if (getClass() != obj.getClass())
			return false;
		if(this.id != other.id)
			return false;
		return Objects.equals(name, other.name);      
		
	}
	
	

}
