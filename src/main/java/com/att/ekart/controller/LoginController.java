package com.att.ekart.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.att.ekart.model.Login;
import com.att.ekart.model.Product;
import com.att.ekart.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	ResourceLoader rl;
	
	@Autowired 
	LoginService service;
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	
	 @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("login") Login login) {
		 ModelAndView mav = null;
 

		 // Properties properties = new Properties();
		 //Resource resource = null;
		 // InputStream in = null;
		 try {
			 // File f = new ClassPathResource("users.properties").getFile();
			 //in = new FileInputStream(f);
			 System.out.println("Inside loginP");
			 /* resource = rl.getResource("classpath:users.properties");
			 in = resource.getInputStream();
			 properties.load(in);
			 logger.debug("loaded properties");*/
			 if (login != null && login.getUsername() != null && login.getPassword() != null) {
				 System.out.println("Inside if");
				 String props[] = new String[2];
				 props[0] = login.getUsername();
				 props[1] = login.getPassword();

				 boolean validate = service.validateUser(props);

				 if(validate) {
					 logger.debug("loading welcome now"); 
					 mav = new ModelAndView("welcome");
					 mav.addObject("firstname", login.getUsername());					
				 }
				 else {
					 System.out.println("Password is incorrect");
					 mav = new ModelAndView("login");
					 mav.addObject("message", "Error: Username or Password is incorrect!!");
				 }

				 /* if(properties.containsKey(login.getUsername())) {
					 System.out.println("Inside uname");
					 if(properties.getProperty(login.getUsername()).equals(login.getPassword())) {
						 System.out.println("user:"+login.getUsername());
						 mav = new ModelAndView("welcome");
						 //mav.addObject("product", new Product());
						 mav.addObject("firstname", login.getUsername());
						 logger.debug("loading welcome now");
					 }
					 else {
						 System.out.println("Password is incorrect");
						 mav = new ModelAndView("login");
						 mav.addObject("message", "Error: Username or Password is wrong!!");
					 }
				 }
				 else {
					 System.out.println("Username doesn't exist");
					 mav = new ModelAndView("login");
					 mav.addObject("message", "Error: Username or Password is incorrect!!");
				 } */

			 }
			 else {
				 logger.info("login obj is null");
				 mav = new ModelAndView("login");
				 mav.addObject("message", "Error: Username or Password is wrong!!");
			 }

		 }  catch (Exception e) {
			 System.out.println("exp:"+e.getMessage());
			  e.printStackTrace();
		 }

		 return mav;
	 }
	 
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	  public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
	    
	    //mav.addObject("login", new Login());
	    HttpSession session = request.getSession();
	    ModelAndView mav = new ModelAndView("logout");
	    session.invalidate();
	    return mav;
	  }
	 
	 @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	  public ModelAndView homepage(HttpServletRequest request, HttpServletResponse response) {
	    	    
	    HttpSession session = request.getSession();
	    ModelAndView mav = new ModelAndView("welcome");
	    
	    return mav;
	  }
	 
	 @ExceptionHandler({IOException.class, java.lang.Exception.class})
	 public ModelAndView handleIOException(Exception ex) {
		 ModelAndView model = new ModelAndView("error");

		 model.addObject("exception", ex.getMessage());

		 return model;
	 }

}
