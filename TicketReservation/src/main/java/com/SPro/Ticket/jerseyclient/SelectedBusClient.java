package com.SPro.Ticket.jerseyclient;

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

import com.SPro.Ticket.bean.BusSeats;
import com.SPro.Ticket.bean.BusSeatsView;
import com.SPro.Ticket.tools.PropertiesLoading;

public class SelectedBusClient {

	private static final Logger LOGGER = LogManager.getLogger(SelectedBusClient.class);

	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");
	
	/**
	 * 
	 * @param bus
	 * @return
	 */
	public List<BusSeats> postSelectedBus(Bus bus) {
		LOGGER.trace("Inside postSelectedBus method");
		if(null==bus) {
			LOGGER.error("bus null");
			return null;
		}
		LOGGER.trace("Argument name "+bus.getName());
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL).path("/selectedbus");
		
		Response response =null;
		response = webTarget.path("/selectedbuspost")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bus, MediaType.APPLICATION_JSON));
		if(response.getStatus()!=200) {
			LOGGER.trace("response"+response.getStatus());
			return null;
		}
		BusSeatsView busSeatsView = response.readEntity(BusSeatsView.class);
		LOGGER.trace("Leaving postSelectedBus method");
		return busSeatsView.getBusSeats();
	}

}
