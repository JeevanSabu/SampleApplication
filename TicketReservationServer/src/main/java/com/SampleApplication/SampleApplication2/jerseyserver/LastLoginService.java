package com.SampleApplication.SampleApplication2.jerseyserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.LastLoginDao;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;

@Path("/logout")
public class LastLoginService {
	private static final Logger LOGGER = LogManager.getLogger(LastLoginService.class);
	LastLoginDao lastLoginDao = new LastLoginDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(@QueryParam("username") String username,
			@QueryParam("password") String password,
			@QueryParam("lastlogin") String lastlogin)
	{
		LOGGER.trace("inside logout "+username);
		return lastLoginDao.loguot(username,password,lastlogin);
	}
}
