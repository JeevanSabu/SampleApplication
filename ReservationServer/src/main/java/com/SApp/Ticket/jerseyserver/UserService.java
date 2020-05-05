package com.SApp.Ticket.jerseyserver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.SApp.Ticket.dao.UserDao;
import com.SApp.Ticket.pojo.LoginPojo;
import com.SApp.Ticket.pojo.UserPojo;

@Path("/user")
public class UserService {
	private UserDao userDao = new UserDao();
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserPojo getUser( LoginPojo loginPojo ){
    	LOGGER.trace("Inside UserServices getUser method");
		UserPojo userPojo = new UserPojo();
	    if( null==loginPojo ){
	    	LOGGER.error("userPojo null");
	        return null;
	    }
	     
	    else {
			try {
				boolean isvaliduser = ESAPI.validator().isValidInput("username", loginPojo.getUsername(), "username", 30, false);
				boolean isvalidpassword = ESAPI.validator().isValidInput("password", loginPojo.getPassword(), "password", 100, false);
				LOGGER.trace("is valid "+isvaliduser+" "+isvalidpassword);
				if(isvaliduser==false||isvalidpassword==false) {
					return null;
				}
				userPojo =  userDao.getUser(loginPojo.getUsername(),loginPojo.getPassword());
	    		LOGGER.trace("From userPojo "+ userPojo.getUsername());
	    	}catch(Exception e) {
	    		LOGGER.error("Exception "+e.getMessage());
	    		return null;
	    	}
	    }

		LOGGER.trace("Leaving UserServices getUser method");
		return userPojo;
	}
}
