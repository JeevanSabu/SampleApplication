package com.SampleApplication.SampleApplication2.jerseyclient;


import javax.ws.rs.core.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.filter.LoggingFilter;

public class User {
//	public UserPojo getUser(String username,String password){
//		Client client = ClientBuilder.newClient(ClientConfig().register(LoggingFilter.class));
//
//		WebTarget webTarget = client
//				.target("http://localhost:8080/TicketReservation/rest");
//		
//		Response response = webTarget.path("/userlogin")
//				.request(MediaType.APPLICATION_JSON)
//				.QueryParam("username",username)
//				.QueryParam("password",password)
//				.get();
//		UserPojo userPojo = response.readEntity(User.class);
//		return userPojo;
//	}
}
