package com.SApp.Ticket.jerseyclient;

import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.SApp.Ticket.tools.PropertiesLoading;
import com.SApp.Ticket.tools.SessionUtils;

public class HomeClient {

	private static final Logger LOGGER = LogManager.getLogger(HomeClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");

	/**
	 * Client method to fetch teh list of buses
	 * on the specified date
	 * from and to specified source and destination
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 */
	public BusViewPojo postBuses(String source, String destination, String date) {
		LOGGER.trace("Inside HomeClient getBuses method ");
		if(null==source||null==destination||null==date) {
			LOGGER.error("Source , destination or date is null");
			return null;
		}
		LOGGER.trace("Argument source "+source);

		HttpSession session = SessionUtils.getSession();
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL);
		Response response =null;
		Form form = new Form()
				.param("source",source)
				.param("destination",destination)
				.param("date",date);
		try {
			response = webTarget.path("/buslist")
					.request()
				    .header("username",session.getAttribute("username"))
				    .header("token",session.getAttribute("token"))
	                .post(Entity.form(form));
			LOGGER.trace("Status "+response.getStatus());
			if(response.getStatus()!=200) {
				return null;
			}
			BusViewPojo busViewPojo = response.readEntity(BusViewPojo.class);
			return busViewPojo;
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.trace("Leaving getBuses method");
		return null;
	}
}
