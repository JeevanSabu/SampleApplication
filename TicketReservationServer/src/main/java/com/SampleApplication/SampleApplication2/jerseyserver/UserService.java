package com.SampleApplication.SampleApplication2.jerseyserver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.LoginPojo;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;

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
				userPojo =  userDao.getUser(loginPojo.getUsername(),loginPojo.getPassword());
	    		LOGGER.trace("From userPojo "+ userPojo.getUsername());
	    	}catch(Exception e) {
	    		LOGGER.error("Exception "+e.getMessage());
	    		return null;
	    	}
	    }
//	    if(e.getName() == null) {
//	        return Response.status(400).entity("Please provide the employee name !!").build();
//	    }

		LOGGER.trace("Leaving UserServices getUser method");
		return userPojo;
	}
}
