package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.ArrayList;
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

import com.SampleApplication.SampleApplication2.tools.Bus;
import com.SampleApplication.SampleApplication2.tools.BusView;

public class HomeClient{
	private static final Logger LOGGER = LogManager.getLogger(HomeClient.class);
	
	public List<Bus> search(String source, String destination, Date date) throws MessageBodyProviderNotFoundException{
		
		LOGGER.trace("argument date "+date);
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest/home");
		Response response =null;
		List<Bus> buses = new ArrayList<Bus>();
		try {
			response = webTarget.path("/search")
					.queryParam("source",source)
					.queryParam("destination",destination)
					.queryParam("date",date)
					.request(MediaType.APPLICATION_JSON)
					.get(Response.class);
			LOGGER.trace("Status "+response.getStatus());
//			BusView busView = response.readEntity(BusView.class);
//			List<Bus> buses = busView.getBuses();
			
			buses = response.readEntity(BusView.class).getBuses();
			
//		}catch(MessageBodyProviderNotFoundException me) {
//			LOGGER.error("Message error "+me.getMessage());
		}catch(Exception e) {
			LOGGER.error("Exception "+e);
		}
		return buses;
	}
}
