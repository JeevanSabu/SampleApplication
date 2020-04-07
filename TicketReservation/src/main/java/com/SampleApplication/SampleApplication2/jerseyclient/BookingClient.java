package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingClient {
	private static final Logger LOGGER = LogManager.getLogger(BookingClient.class);

	public BookingsPojo book(int busid, String username, List<String> seatnos, List<String> passname, List<Integer> passage, List<String> passgender) {
		LOGGER.trace("Argument "+seatnos.get(0));
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest/booking");
		Response response =null;
		try {
			response = webTarget.path("bookingseats")
					.queryParam("busid", busid)
					.queryParam("username", username)
					.queryParam("seatnos", seatnos)
					.queryParam("passname", passname)
					.queryParam("passage", passage)
					.queryParam("passgender", passgender)
					.request(MediaType.APPLICATION_JSON)
					.get(Response.class);
			LOGGER.trace("Response "+response.getStatus());
			BookingsPojo bookingsPojo = response.readEntity(BookingsPojo.class);
			return bookingsPojo;
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
