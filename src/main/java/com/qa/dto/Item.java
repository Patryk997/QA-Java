package com.qa.dto;

import java.util.Objects;

public class Item {

	Integer id;
	String name; 
	Double value;
	
	public Item(Integer id, String name, Double value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public Item(String name, Double value) {
		this.name = name;
		this.value = value;
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
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if (!(obj instanceof Item)) {
			return false;
		}
		Item other = (Item) obj;
		if (getClass() != obj.getClass())
			return false;
		if(!Objects.equals(id, other.id))
			return false;
		if(!Objects.equals(value, other.value))
			return false;

		return Objects.equals(name, other.name);      
		
	}
	
	
}
