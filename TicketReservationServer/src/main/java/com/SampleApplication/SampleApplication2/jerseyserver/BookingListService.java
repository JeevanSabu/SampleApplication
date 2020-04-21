package com.SampleApplication.SampleApplication2.jerseyserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.BookingListDao;
import com.SampleApplication.SampleApplication2.pojo.BookingListPojo;

@Path("/bookinglist")
public class BookingListService {
	
	private static final Logger LOGGER = LogManager.getLogger(BookingListService.class);
 
	private BookingListDao bookingListDao = new BookingListDao();

	@POST
	@Path("/bookinglistpost")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public BookingListPojo bookinglist(@FormParam("username") String username) {
		LOGGER.trace("Inside BookingLIstService bookingList method ");
		LOGGER.trace("From from param "+ username);
		BookingListPojo bookingListPojo = bookingListDao.getBookingList(username);
		LOGGER.trace("Leaving BookingLIstService bookingList method ");
		return bookingListPojo;
	}
}
