package com.SampleApplication.SampleApplication2.jerseyserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.BookingDao;
import com.SampleApplication.SampleApplication2.dao.BookingsDao;
import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.BookingDetails;
import com.SampleApplication.SampleApplication2.pojo.BookingsPojo;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
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

	@GET
	@Path("/bookingseats")
	@Produces(MediaType.APPLICATION_JSON)
	public BookingsPojo getBookings(@QueryParam("busid") int busid,
			@QueryParam("username") String username,
			@QueryParam("seatnos") String seatnos,
			@QueryParam("passname") String passname,
			@QueryParam("passage") String passage,
			@QueryParam("passgender") String passgender) {
		LOGGER.trace("Inside getBookings method");
		try {
			seatnos = seatnos.replaceAll("[\\[\\]]", "");
			passname = passname.replaceAll("[\\[\\]]", "");
			passage = passage.replaceAll("[\\[\\]]", "");
			passgender =passgender.replaceAll("[\\[\\]]", "");
			String[] seatnoslist = seatnos.split(",");
			String[] passnamelist = passname.split(",");
			String[] passagelist = passage.split(",");
			String[] passgenderlist = passgender.split(",");
			LOGGER.trace("From query param "+ seatnos);
			BookingsPojo bookingsPojo = bookingDao.getBookings(busid,username,seatnoslist,passnamelist,passagelist,passgenderlist);
			LOGGER.trace("From query bookingsPojo "+ bookingsPojo.getBusname());
			return bookingsPojo;
		}catch(NullPointerException ne) {
			LOGGER.error("Exception "+ne.getMessage());	
		}catch(Exception e) {
				LOGGER.error("Exception "+e.getMessage());	
		}
		LOGGER.trace("Leaving getBookings method");
		return null;
	}
	@GET
	@Path("/bookingpost")
	@Produces(MediaType.APPLICATION_JSON)
	public BookingsPojo getBookings(BookingDetails bookingDetails) {
		LOGGER.trace("Inside getBookings method");
		try {
			BookingsPojo bookingsPojo = bookingsDao.getBookings(bookingDetails.getBusId(),bookingDetails.getUsername(),bookingDetails.getAvailableSeats(),bookingDetails.getSeats());
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
