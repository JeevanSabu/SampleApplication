package com.SampleApplication.SampleApplication2.jerseyclient;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.SampleApplication.SampleApplication2.tools.PropertiesLoading;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class User {
	private static final Logger LOGGER = LogManager.getLogger(User.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserPojo getUser(String username,String password){
		LOGGER.trace(BASE_URL);
		LOGGER.trace("Argument username "+username);

		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL).path("/user");
		Response response =null;
		try {
		response = webTarget.path("/userlogin")
				.queryParam("username",username)
				.queryParam("password",password)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		LOGGER.trace("Status "+response.getStatus());
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
