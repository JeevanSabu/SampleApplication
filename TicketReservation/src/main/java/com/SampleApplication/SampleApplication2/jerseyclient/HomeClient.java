package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;

import com.SampleApplication.SampleApplication2.bean.BusView;

public class HomeClient {

	private static final Logger LOGGER = LogManager.getLogger(HomeClient.class);

	public BusViewPojo getBuses(String source, String destination) {

		LOGGER.trace("Argument source "+source);
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest");
		Response response =null;
		try {
			response = webTarget.path("/buslist")
					.queryParam("source",source)
					.queryParam("destination",destination)
					.request(MediaType.APPLICATION_JSON)
					.get(Response.class);
			LOGGER.trace("Status "+response.getStatus());
			BusViewPojo busViewPojo = response.readEntity(BusViewPojo.class);
			return busViewPojo;
		} catch(MessageBodyProviderNotFoundException me) {
			LOGGER.error(me.getMessage());
			return null;
		}
	}

}
