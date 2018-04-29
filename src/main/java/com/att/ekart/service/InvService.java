package com.att.ekart.service;

import java.util.List;

import com.att.ekart.model.Product;

public interface InvService {

	public Product addItem(Product prod);
	public String updateItem(Product prod, String id);
	public String deleteItem(String id);
	public List listItems();
	
}
