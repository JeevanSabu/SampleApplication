package com.SPro.Ticket.jerseyserver;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SPro.Ticket.dao.BookingListDao;
import com.SPro.Ticket.pojo.BookingListPojo;

@Path("/bookinglist")
public class BookingListService {
	
	private static final Logger LOGGER = LogManager.getLogger(BookingListService.class);
 
	private BookingListDao bookingListDao = new BookingListDao();

    @RolesAllowed("ADMIN")
	@POST
	@Path("/bookinglistpost")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public BookingListPojo bookinglist(@FormParam("username") String username) {
		LOGGER.trace("Inside BookingLIstService bookingList method ");
		if(null==username) {
			LOGGER.error("username NULL");
			return null;
		}
		boolean isvaliduser = ESAPI.validator().isValidInput("username", username, "username", 30, false);
		LOGGER.trace("is valid "+isvaliduser);
		if(isvaliduser==false) {
			return null;
		}
		LOGGER.trace("From from param "+ username);
		BookingListPojo bookingListPojo = bookingListDao.getBookingList(username);
		LOGGER.trace("Leaving BookingLIstService bookingList method ");
		return bookingListPojo;
	}
}
