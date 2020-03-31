package com.SampleApplication.SampleApplication2.jerseyserver;

import javax.ws.rs.core.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;
@Path("/user")
public class UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	UserDao userDao = new UserDao();

	@GET
	@Path("/userlogin")
	@Produces(MediaType.APPLICATION_JSON)
	public UserPojo getUser(@QueryParam ("username") String username,@QueryParam ("password") String password) {
		UserPojo userPojo = userDao.getUser(username,password);
		return userPojo;
	}
}
