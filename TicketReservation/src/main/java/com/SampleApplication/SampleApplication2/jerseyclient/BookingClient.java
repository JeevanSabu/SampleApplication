package com.SampleApplication.SampleApplication2.jerseyclient;

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

import com.SampleApplication.SampleApplication2.bean.PaymentBean;
import com.SampleApplication.SampleApplication2.bean.Seats;
import com.SampleApplication.SampleApplication2.tools.PropertiesLoading;

public class BookingClient {
	private static final Logger LOGGER = LogManager.getLogger(BookingClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");
	
	/**
	 * 
	 * @param busid
	 * @param username
	 * @param seatnos
	 * @param passname
	 * @param passage
	 * @param passgender
	 * @return
	 */
	public BookingsPojo getBookings(int busid,
			String username,
			List<String> seatnos,
			List<String> passname,
			List<Integer> passage,
			List<String> passgender){
		LOGGER.trace("Argument username "+username);
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL).path("/booking");
		Response response =null;
		try {
		response = webTarget.path("/bookingseats")
				.queryParam("busid", busid)
				.queryParam("username", username)
				.queryParam("seatnos", seatnos.toString())
				.queryParam("passname", passname.toString())
				.queryParam("passage", passage.toString())
				.queryParam("passgender", passgender.toString())
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);

		LOGGER.trace("Status "+response.getStatus());
		if(response.getStatus()!=200) {
			return null;
		}
		BookingsPojo bookingsPojo = response.readEntity(BookingsPojo.class);
		return bookingsPojo;
		}catch(Exception e) {
			LOGGER.error("Error:"+e.getMessage());
			return null;
		}
		
	}
	
	public BookingsPojo getBookings(String username, PaymentBean paymentBean){
		LOGGER.trace("Argument busname "+paymentBean.getBusName());
		
		BookingDetails bookingDetails =new BookingDetails();
		bookingDetails.setUsername(username);
		bookingDetails.setBusId(paymentBean.getBusId());
		bookingDetails.setBusName(paymentBean.getBusName());
		bookingDetails.setPrice(paymentBean.getPrice());
		bookingDetails.setSeats(paymentBean.getSeats());
		bookingDetails.setAvailableSeats(paymentBean.getAvailableSeats());
		
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
			return null;
		}
		
	}
}
