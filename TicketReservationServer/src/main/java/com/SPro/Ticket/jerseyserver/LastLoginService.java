package com.SPro.Ticket.jerseyserver;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SPro.Ticket.dao.LastLoginDao;
import com.SPro.Ticket.pojo.LoginPojo;
import com.SPro.Ticket.pojo.UserPojo;

@Path("/logout")
public class LastLoginService {
	private static final Logger LOGGER = LogManager.getLogger(LastLoginService.class);
	LastLoginDao lastLoginDao = new LastLoginDao();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(LoginPojo loginPojo){
		LOGGER.trace("inside logout ");
		if(null==loginPojo) {
			LOGGER.error("LoginPojo null");
			return null;
		}
		Date date = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("d MMM y HH:mm:ss");
	    String formatteddate = ft.format(date).toString();
	    LOGGER.trace("Logout at "+formatteddate);
		return lastLoginDao.loguot(loginPojo.getUsername(),loginPojo.getPassword(),formatteddate);
	}
}
