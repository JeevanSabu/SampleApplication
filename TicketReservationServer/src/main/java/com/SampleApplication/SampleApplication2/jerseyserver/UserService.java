package com.SampleApplication.SampleApplication2.jerseyserver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;
@Path("/user")
public class UserService {
	private UserDao userDao = new UserDao();
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	@GET
	@Path("/userlogin")
	@Produces(MediaType.APPLICATION_JSON)
	public UserPojo getUser(@QueryParam("username") String username,@QueryParam("password") String password) {
		try {
		LOGGER.trace("From query param "+ username);
		UserPojo userPojo = userDao.getUser(username,password);
		LOGGER.trace("From userPojo "+ userPojo.getUsername());
		return userPojo;
		}catch(Exception e) {
			LOGGER.error("Exception "+e.getMessage());
			return null;
		}
	}
	@GET
	@Path("/listall")
	@Produces(MediaType.APPLICATION_JSON)
	public String getlist() {
//		UserPojo userPojo = userDao.getList();
		return "Testing";
	}
}
