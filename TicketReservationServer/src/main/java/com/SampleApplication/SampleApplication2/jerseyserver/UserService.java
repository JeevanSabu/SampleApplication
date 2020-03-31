package com.SampleApplication.SampleApplication2.jerseyserver;

import javax.ws.rs.core.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.User;

@GET
@Path("/userlogin")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	UserDao userDao = new UserDao();
	public User getUser(@QueryParam ("username") String username,@QueryParam ("password") String password) {
		User user = userDao.getUser();
		return null;
	}
}
