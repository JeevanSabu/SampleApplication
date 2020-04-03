package com.SampleApplication.SampleApplication2.model;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.tools.Bus;
import com.SampleApplication.SampleApplication2.tools.BusView;

public class HomeClient {
	private static final Logger LOGGER = LogManager.getLogger(HomeClient.class);
	
	public List<Bus> search(String source, String destination, Date date) {
		
		LOGGER.trace("argument date "+date);
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest/home");
		Response response =null;
		try {
			response = webTarget.path("/search")
					.queryParam("source",source)
					.queryParam("destination",destination)
					.queryParam("date",date)
					.request(MediaType.APPLICATION_JSON)
					.get();
			LOGGER.trace("Status "+response.getStatus());
//			BusView busView = response.readEntity(BusView.class);
//			List<Bus> buses = busView.getBuses();
			
			List<Bus> buses = response.readEntity(BusView.class).getBuses();
			
			return buses;
		}catch(Exception e) {
			LOGGER.error("Exception "+e);
		}
		return null;
	}
}
