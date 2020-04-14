package com.SampleApplication.SampleApplication2.jerseyserver;

import javax.ws.rs.GET;
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BookingListPojo getBookinglist(@QueryParam("username") String username) {
		LOGGER.trace("From query param "+ username);
		BookingListPojo bookingListPojo = bookingListDao.getBookingList(username);
		return bookingListPojo;
	}
}
