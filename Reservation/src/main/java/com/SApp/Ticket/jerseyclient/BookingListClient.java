package com.SApp.Ticket.jerseyclient;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.SApp.Ticket.tools.PropertiesLoading;
import com.SApp.Ticket.tools.SessionUtils;

public class BookingListClient {

	private static final Logger LOGGER = LogManager.getLogger(BookingListClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");

	/**
	 * Client method to fetch previous bookings
	 * of the user
	 * @param username
	 * @return
	 */
	public List<BookingsPojo> postBookingList(String username) {
		LOGGER.trace("Inside BokkingLIst Client postBooking method");
		if(null==username) {
			LOGGER.error("username null");
			return null;
		}
		LOGGER.trace("Argument username "+username);

		HttpSession session = SessionUtils.getSession();
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(BASE_URL).path("/bookinglist");
		Response response =null;
		Form form = new Form()
                .param("username", username);
		response = webTarget.path("/bookinglistpost")
				.request(MediaType.APPLICATION_JSON)
			    .header("username",session.getAttribute("username"))
			    .header("token",session.getAttribute("token"))
                .post(Entity.form(form));
		if(response.getStatus()==200) {
			BookingListPojo bookingListPojo = response.readEntity(BookingListPojo.class);
			List<BookingsPojo> list = bookingListPojo.getBookingList();
			return list;
		}
		LOGGER.trace("Leaving BookingLIst Client postBooking method");
		return null;
	}
}
