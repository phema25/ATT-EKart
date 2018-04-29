package com.att.ekart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.att.ekart.model.Login;
import com.att.ekart.model.Product;
import com.att.ekart.model.RequestWrapper;
import com.att.ekart.service.InvService;

@RestController
@Controller
public class InventoryController {
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	HashMap map = null;
	List list = null;
	
	@Autowired
	InvService service;

	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("addItem");
		mav.addObject("product", new Product());
		return mav;
	}

	@RequestMapping(value = "/updateItem", method = RequestMethod.GET)
	public ModelAndView updateItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("updateItem");
		mav.addObject("product", new Product());
		return mav;
	}
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
	public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("deleteItem");
		//mav.addObject("product", new Product());
		return mav;
	}

	@RequestMapping(value = "/addProcess", method = RequestMethod.POST)
	public @ResponseBody Product addProcess(@RequestBody Product prod) {
		ModelAndView mav = null;

		/*  if(map == null)
            	map = new HashMap();*/
		System.out.println("add prod");
		
		Product addProd = service.addItem(prod);
		return addProd;
		
		
		//map.put("p"+map.size()+1, prod);
		/*if(list == null)
			list = new ArrayList();

		if(map == null)
			map = new HashMap();

		map.put(prod.getId(), prod);*/

		///////// list.add(map);

		/* Product padd = (Product)list.get(0);
           System.out.println("value:"+padd.getId());
           System.out.println("value:"+padd.getName());*/
		// mav = new ModelAndView("welcome");
		//mav.addObject("message", "Product added successfully!");///////// old 
	/*	System.out.println("value:"+prod.getDesc());
		System.out.println("value:"+prod.getName());
		System.out.println("added prod");
		logger.info("Product added:"+map.get(prod.getId()));
		prodInv.setMap(map);
		return (Product)map.get(prod.getId()); */
	}


	@RequestMapping(value = "/updateProcess", method = RequestMethod.POST)
	public @ResponseBody String updateProcess(@RequestBody RequestWrapper reqWrapper ) {

		
			//ModelAndView mav = null;
		    logger.info("Inside update controller");
			String id = reqWrapper.getId();
			logger.info("id:"+id);
			
           Product prod = reqWrapper.getProduct();
           
           String message = service.updateItem(prod, id);
           
           return message;
           
			/*HashMap map = prodInv.getMap();
			System.out.println("Before update map:"+map);
			if(map != null && map.size() > 0) {
				if(id!= null && map.containsKey(id)) {
					Product oldProd = (Product)map.get(id);
					Product newProd = reqWrapper.getProduct();
					logger.info("new prod:"+newProd.getId());
					logger.info("Old prod:"+((Product)map.get(id)).getName());
					System.out.println("new prod id:"+newProd.getId());

					if(id == newProd.getId()) {

						oldProd.setDesc(newProd.getDesc());
						oldProd.setName(newProd.getName());
						map.put(oldProd.getId(), oldProd);
						System.out.println("updated existing ID:");
					}
					else {
						map.remove(id);
						map.put(newProd.getId(), newProd);
						System.out.println("added new prod id:"+newProd.getId());
					}					
					logger.info("Product updated:"+map.get(newProd.getId()));
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
		}*/

	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody String deleteProcess(@PathVariable("id") String id) {
			
		logger.info("delete controller");		
		String message = service.deleteItem(id);
		return message;

		/*try {
			ModelAndView mav = null;

			System.out.println("ID to delete:"+id);
			logger.info("id:"+id);
			HashMap map = prodInv.getMap();
			
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
		} */

	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List listProcess() {
		
		
		logger.info("list controller");
		List list = service.listItems();
		return list;

		/*try {
			//ModelAndView mav = null;


			logger.info("List of items from the inventory:");
			HashMap map = prodInv.getMap();
			List list = null;

			//System.out.println("Before delete map:"+map);
			if(map != null && map.size() > 0) {
				list = new ArrayList();
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
		}*/

	}

	 @ExceptionHandler({IOException.class, java.lang.Exception.class})
	 public ModelAndView handleIOException(Exception ex) {
		 ModelAndView model = new ModelAndView("error");

		 model.addObject("exception", ex.getMessage());

		 return model;
	 }

}

