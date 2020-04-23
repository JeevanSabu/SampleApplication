package com.SampleApplication.SampleApplication2.jerseyserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.BookingDao;
import com.SampleApplication.SampleApplication2.dao.BookingsDao;
import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.BookingDetails;
import com.SampleApplication.SampleApplication2.pojo.BookingsPojo;
import com.SampleApplication.SampleApplication2.pojo.Seats;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/booking")
public class BookingService {
	private static final Logger LOGGER = LogManager.getLogger(BookingService.class);

	private BookingDao bookingDao = new BookingDao();
	private BookingsDao bookingsDao = new BookingsDao();

	@POST
	@Path("/bookingpost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BookingsPojo getBookings(BookingDetails bookingDetails) {
		LOGGER.trace("Inside getBookings method");
		if(null==bookingDetails) {
			LOGGER.error("bookingDetails is null");
			return null;
		}
		List<Seats> seats = new ArrayList<Seats>();
		try {
			for(int i=0;i<bookingDetails.getSeatnos().size();i++) {
				seats.add(new Seats(bookingDetails.getSeatnos().get(i),
						bookingDetails.getPassname().get(i),
						bookingDetails.getPassage().get(i),
						bookingDetails.getPassgender().get(i)));
			}
			BookingsPojo bookingsPojo = bookingsDao.getBookings(bookingDetails.getBusId(),bookingDetails.getUsername(),bookingDetails.getAvailableSeats(),seats);
			LOGGER.trace("From query bookingsPojo "+ bookingsPojo.getBusname());
			return bookingsPojo;
		} catch(NullPointerException ne) {
			LOGGER.error("Exception "+ne.getMessage());	
		} catch(Exception e) {
			LOGGER.error("Exception "+e.getMessage());	
		}

		LOGGER.trace("Leaving getBookings method");
		return null;
	}
}
