package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.Date;
import java.util.List;

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
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;

import com.SampleApplication.SampleApplication2.bean.BusView;
import com.SampleApplication.SampleApplication2.tools.PropertiesLoading;

public class HomeClient {

	private static final Logger LOGGER = LogManager.getLogger(HomeClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 */
	public BusViewPojo postBuses(String source, String destination, String date) {
		LOGGER.trace("Inside HomeClient getBuses method ");
		LOGGER.trace("Argument source "+source);

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
	                .post(Entity.form(form));
			LOGGER.trace("Status "+response.getStatus());
			BusViewPojo busViewPojo = response.readEntity(BusViewPojo.class);
			return busViewPojo;
		} catch(MessageBodyProviderNotFoundException me) {
			LOGGER.error(me.getMessage());
			return null;
		}
	}
}
