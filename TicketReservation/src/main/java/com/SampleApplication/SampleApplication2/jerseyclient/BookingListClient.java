package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingListClient {

	private static final Logger LOGGER = LogManager.getLogger(BookingListClient.class);

	public List<BookingsPojo> getBookingList(String username) {
		LOGGER.trace("Argument username "+username);
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest");
		Response response =null;
		response = webTarget.path("/bookinglist")
				.queryParam("username",username)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		BookingListPojo bookingListPojo = response.readEntity(BookingListPojo.class);
		List<BookingsPojo> list = bookingListPojo.getBookingList();
		return list;
	}

}
