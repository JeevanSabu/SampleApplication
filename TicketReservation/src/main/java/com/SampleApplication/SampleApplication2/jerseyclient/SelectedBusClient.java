package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.SampleApplication.SampleApplication2.bean.BusSeats;
import com.SampleApplication.SampleApplication2.bean.BusSeatsView;
import com.SampleApplication.SampleApplication2.tools.PropertiesLoading;

public class SelectedBusClient {

	private static final Logger LOGGER = LogManager.getLogger(SelectedBusClient.class);

	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");
	
	public List<BusSeats> getSelectedBus(String id, String name) {
		LOGGER.trace("Argument name "+name);
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL);
		
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
