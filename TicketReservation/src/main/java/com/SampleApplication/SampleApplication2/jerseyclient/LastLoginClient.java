package com.SampleApplication.SampleApplication2.jerseyclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LastLoginClient {
	private static final Logger LOGGER = LogManager.getLogger(LastLoginClient.class);

	public int logout(String username, String password, String lastlogin) {
		LOGGER.trace("inside logout");
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest");
		Response response =null;
		response = webTarget.path("/logout")
				.queryParam("username",username)
				.queryParam("password",password)
				.queryParam("lastlogin",lastlogin)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		LOGGER.trace("Status "+response.getStatus());
		return response.getStatus();
	}
}
