package com.SampleApplication.SampleApplication2.jerseyserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.BookingDao;
import com.SampleApplication.SampleApplication2.dao.UserDao;
import com.SampleApplication.SampleApplication2.pojo.BookingsPojo;
import com.SampleApplication.SampleApplication2.pojo.UserPojo;

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

	@GET
	@Path("/bookingseats")
	@Produces(MediaType.APPLICATION_JSON)
	public BookingsPojo getBookings(@QueryParam("busid") int busid,
			@QueryParam("username") String username,
			@QueryParam("seatnos") List<String> seatnos,
			@QueryParam("passname") List<String> passname,
			@QueryParam("passage") List<Integer> passage,
			@QueryParam("passgender") List<String> passgender) {
		try {
			LOGGER.trace("From query param "+ seatnos.get(0));
			BookingsPojo bookingsPojo = bookingDao.getBookings(busid,username,seatnos,passname,passage,passgender);
			LOGGER.trace("From query bookingsPojo "+ bookingsPojo.getBusname());
			return bookingsPojo;
		}catch(Exception e) {
				LOGGER.error("Exception "+e.getMessage());	
				return null;
		}
	}
}
