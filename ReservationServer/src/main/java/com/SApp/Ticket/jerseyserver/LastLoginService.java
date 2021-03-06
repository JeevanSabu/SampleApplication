package com.SApp.Ticket.jerseyserver;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SApp.Ticket.dao.LastLoginDao;
import com.SApp.Ticket.pojo.LoginPojo;

@Path("/logout")
public class LastLoginService {
	private static final Logger LOGGER = LogManager.getLogger(LastLoginService.class);
	LastLoginDao lastLoginDao = new LastLoginDao();

    @RolesAllowed("ADMIN")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(LoginPojo loginPojo){
		LOGGER.trace("inside logout ");
		if(null==loginPojo) {
			LOGGER.error("LoginPojo null");
			return null;
		}
		
		boolean isvaliduser = ESAPI.validator().isValidInput("username", loginPojo.getUsername(), "username", 30, false);
		boolean isvalidpassword = ESAPI.validator().isValidInput("password", loginPojo.getPassword(), "password", 100, false);
		LOGGER.trace("is valid "+isvaliduser+" "+isvalidpassword);
		if(isvaliduser==false||isvalidpassword==false) {
			LOGGER.error("Esapi validation returned false");
			return null;
		}
		
		Date date = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("d MMM y HH:mm:ss");
	    String formatteddate = ft.format(date).toString();
	    LOGGER.trace("Logout at "+formatteddate);
		return lastLoginDao.loguot(loginPojo.getUsername(),loginPojo.getPassword(),formatteddate);
	}
}
