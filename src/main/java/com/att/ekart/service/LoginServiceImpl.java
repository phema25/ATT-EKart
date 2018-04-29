package com.att.ekart.service;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;



@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	ResourceLoader rl;
	
	@Override
	public boolean validateUser(String[] props) {
		// TODO Auto-generated method stub
		String uname = null;
		String pwd = null;

		Properties properties = new Properties();
		Resource resource = null;		 
		InputStream in = null;

		try {
			
			logger.info("Inside validate user");
			resource = rl.getResource("classpath:users.properties");
			in = resource.getInputStream();
			properties.load(in);
			logger.debug("loaded properties");

			if(props != null && props.length > 0) {
				if(properties != null && properties.containsKey(props[0])) {
					if(properties.get(props[0]).equals(props[1]))
						return true;
					else 
						return false;
				}
				else 
					return false;
			}

		} catch (Exception e) {
			System.out.println("exp:"+e.getMessage());
			logger.info("exp:"+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
