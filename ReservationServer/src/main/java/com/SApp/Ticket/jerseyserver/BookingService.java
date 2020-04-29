package com.SApp.Ticket.jerseyserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SApp.Ticket.dao.BookingsDao;
import com.SApp.Ticket.pojo.BookingDetails;
import com.SApp.Ticket.pojo.BookingsPojo;
import com.SApp.Ticket.pojo.Seats;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/booking")
public class BookingService {
	private static final Logger LOGGER = LogManager.getLogger(BookingService.class);

	private BookingsDao bookingsDao = new BookingsDao();

    @RolesAllowed("ADMIN")
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
		boolean isvaliduser = ESAPI.validator().isValidInput("username", bookingDetails.getUsername(), "username", 30, false);
		LOGGER.trace("is valid "+isvaliduser);
		if(isvaliduser==false) {
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
