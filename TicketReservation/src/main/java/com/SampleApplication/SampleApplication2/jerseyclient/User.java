package com.SampleApplication.SampleApplication2.jerseyclient;


import javax.ws.rs.core.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public class User {
	private static final Logger LOGGER = LogManager.getLogger(User.class);

	public String getUser(String username,String password){
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservation/rest/user");
		
		Response response = webTarget.path("/userlogin")
				.queryParam("username",username)
				.queryParam("password",password)
				.request(MediaType.APPLICATION_JSON)
				.get();
		String status = response.readEntity(String.class);
//		UserPojo userPojo = response.readEntity(UserPojo.class);
//		LOGGER.trace("UserPojo "+userPojo.getUsername());
//		return userPojo.getUsername();
		LOGGER.trace("Status "+status);
		return status;
	}
}
