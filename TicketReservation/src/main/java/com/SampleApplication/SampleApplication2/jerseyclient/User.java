package com.SampleApplication.SampleApplication2.jerseyclient;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class User {
	private static final Logger LOGGER = LogManager.getLogger(User.class);

	public UserPojo getUser(String username,String password){
		LOGGER.trace("Argument username "+username);
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client
				.target("http://localhost:8080/TicketReservationServer/rest/user");
		Response response =null;
		try {
		response = webTarget.path("/userlogin")
				.queryParam("username",username)
				.queryParam("password",password)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		}catch(Exception e) {
			LOGGER.error("Error:"+e.getMessage());
		}
//		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
//		WebTarget webTarget = client.target("http://localhost:8080/TicketReservationServer/rest/user").path("userlogin");
//		 
//		InvocationBuilder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
//		Response response = invocationBuilder.get();
		
//		String status = response.readEntity(String.class);
		UserPojo userPojo = response.readEntity(UserPojo.class);
//		LOGGER.trace("UserPojo "+userPojo.getUsername());
//		return userPojo.getUsername();
		LOGGER.trace("Status "+response.getStatus());
		if(response.getStatus()!=200) {
			return null;
		}
		return userPojo;
	}
}
