package com.att.ekart.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Product {

	String id;
	String name;
	String desc;
	
	@JsonCreator
	public Product(){}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
}
