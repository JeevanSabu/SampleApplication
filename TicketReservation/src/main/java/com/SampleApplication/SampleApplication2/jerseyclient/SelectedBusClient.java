package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BusSeats;
import com.SampleApplication.SampleApplication2.bean.BusSeatsView;

public class SelectedBusClient {

	private static final Logger LOGGER = LogManager.getLogger(SelectedBusClient.class);

	public List<BusSeats> getSelectedBus(String id, String name) {
		LOGGER.trace("Argument name "+name);
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/TicketReservationServer/rest");
		Response response =null;
		response = webTarget.path("/selectedbus")
				.queryParam("id",id)
				.queryParam("name", name)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		BusSeatsView busSeatsView = response.readEntity(BusSeatsView.class);
		return busSeatsView.getBusSeats();
	}

}
