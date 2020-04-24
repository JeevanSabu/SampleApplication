package com.SPro.Ticket.jerseyclient;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;

import com.SPro.Ticket.bean.PaymentBean;
import com.SPro.Ticket.bean.Seats;
import com.SPro.Ticket.tools.PropertiesLoading;

public class BookingClient {
	private static final Logger LOGGER = LogManager.getLogger(BookingClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");
	
	/**
	 * 
	 * @param username
	 * @param paymentBean
	 * @return
	 */
	public BookingsPojo getBookings(String username, PaymentBean paymentBean){
		LOGGER.trace("Inside getBookings method");
		if(null==username||null==paymentBean) {
			LOGGER.error("username or paymentBean null");
			return null;
		}
		LOGGER.trace("Argument busname "+paymentBean.getBusName());
		
		List<String> seatnos = new ArrayList<String>();
		List<String> passname = new ArrayList<String>();
		List<Integer> passage = new ArrayList<Integer>();
		List<String> passgender = new ArrayList<String>();
		for(Seats seats:paymentBean.getSeats()) {
			seatnos.add(seats.getSeatNo());
			passname.add(seats.getPassengerName());
			passage.add(seats.getPassengerAge());
			passgender.add(seats.getPassengerGender());
		}
		BookingDetails bookingDetails =new BookingDetails();
		bookingDetails.setUsername(username);
		bookingDetails.setBusId(paymentBean.getBusId());
		bookingDetails.setBusName(paymentBean.getBusName());
		bookingDetails.setPrice(paymentBean.getPrice());
		bookingDetails.setAvailableSeats(paymentBean.getAvailableSeats());
		bookingDetails.setSeatnos(seatnos);
		bookingDetails.setPassname(passname);
		bookingDetails.setPassage(passage);
		bookingDetails.setPassgender(passgender);
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL).path("/booking");
		Response response =null;
		try {
		response = webTarget.path("/bookingpost")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bookingDetails, MediaType.APPLICATION_JSON));
		LOGGER.trace("Status "+response.getStatus());

		if(response.getStatus()!=200) {
			return null;
		}
		BookingsPojo bookingsPojo = response.readEntity(BookingsPojo.class);
		return bookingsPojo;
		}catch(Exception e) {
			LOGGER.error("Error:"+e.getMessage());
		}
		LOGGER.trace("Leaving getBookings method");
		return null;
	}
}
