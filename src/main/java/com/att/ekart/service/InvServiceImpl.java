package com.att.ekart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.att.ekart.DAO.ProdInv;

import com.att.ekart.model.Product;

@Service
public class InvServiceImpl implements InvService {
	
	private static final Logger logger = LoggerFactory.getLogger(InvServiceImpl.class);
	
	HashMap map = null;
	List list = null;
	
	@Autowired
	ProdInv prodInv;

	@Override
	public Product addItem(Product prod) {
		// TODO Auto-generated method stub
		if(map == null)
			map = new HashMap<String,Product>();

		map.put(prod.getId(), prod);
		
		System.out.println("value:"+prod.getDesc());
		System.out.println("value:"+prod.getName());
		System.out.println("added prod");
		logger.info("Product added:"+map.get(prod.getId()));
		prodInv.setMap(map);
		return (Product)map.get(prod.getId());
	}

	@Override
	public String updateItem(Product prod, String id) {
		// TODO Auto-generated method stub
		HashMap<String,Product> map = prodInv.getMap();
		System.out.println("Before update map:"+map);

		try {
			if(map != null && map.size() > 0) {
				if(id!= null && map.containsKey(id)) {
					Product oldProd = (Product)map.get(id);

					logger.info("new prod:"+prod.getId());
					logger.info("Old prod:"+((Product)map.get(id)).getName());
					System.out.println("new prod id:"+prod.getId());

					if(id.equals(prod.getId())) {

						oldProd.setDesc(prod.getDesc());
						oldProd.setName(prod.getName());
						map.put(oldProd.getId(), oldProd);
						System.out.println("updated existing ID:");
					}
					else {
						map.remove(id);
						map.put(prod.getId(), prod);
						System.out.println("added new prod id:"+prod.getId());
					}					
					logger.info("Product updated:"+map.get(prod.getId()));
					prodInv.setMap(map);
					System.out.println("after update map:"+map);
					return "Product updated";	
				}else {
					return "Product ID not found. Please enter the correct ID.";
				}
			}
			else {
				return "Empty inventory. Please add items before updating";
			}
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return "exception occurred";
		}
		finally {
			logger.info("Inside update");
		}
	}

	@Override
	public String deleteItem(String id) {
		// TODO Auto-generated method stub
		try {			
			System.out.println("ID to delete:"+id);
			logger.info("id:"+id);
			HashMap<String,Product> map = prodInv.getMap();

			System.out.println("Before delete map:"+map);
			if(map != null && map.size() > 0) {
				System.out.println("Inside delete:");
				System.out.println("total:"+map.size());
				if(id!= null && map.containsKey(id)) {

					Product oldProd = (Product)map.get(id);

					logger.info("Old prod:"+((Product)map.get(id)).getName());

					map.remove(id);
					logger.info("Product deleted:");
					prodInv.setMap(map); //to set the updated map after delete
					return "Product removed";	
				}
				else {
					return "Product ID not found. Please enter the correct ID.";
				}
			}else {
				return "Empty inventory. Please add items before deleting.";
			}
		}catch(Exception ex) {
			logger.info(ex.getMessage());
			System.out.println(ex);
			ex.printStackTrace();
			return "exception occurred";
		}
		finally {
			logger.info("Inside delete");
		}


	}

	@Override
	public List<Product> listItems() {
		// TODO Auto-generated method stub
		try {
			//ModelAndView mav = null;
			logger.info("List of items from the inventory:");
			HashMap<String,Product> map = prodInv.getMap();
			List list = null;

			//System.out.println("Before delete map:"+map);
			if(map != null && map.size() > 0) {
				list = new ArrayList<Product>();
				System.out.println("Inside list:"+list);
				System.out.println("total:"+map.size());
				logger.info("map size:"+map.size());
				Set keys = map.keySet();

				Iterator it = keys.iterator();
				while(it.hasNext()) {
					String key = (String)it.next();
					list.add(map.get(key));
					logger.info(""+key);
					logger.info(""+map.get(key));
				}

				return list;
			}else {
				return list;
			}
		}catch(Exception ex) {
			logger.info(ex.getMessage());
			System.out.println(ex);
			ex.printStackTrace();
			list.add(ex);
			return list;
		}
		finally {
			logger.info("Inside list");
		}
	}

}
