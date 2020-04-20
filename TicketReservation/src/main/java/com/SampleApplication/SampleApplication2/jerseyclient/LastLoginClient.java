package com.SampleApplication.SampleApplication2.jerseyclient;

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

import com.SampleApplication.SampleApplication2.tools.PropertiesLoading;

public class LastLoginClient {
	private static final Logger LOGGER = LogManager.getLogger(LastLoginClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");

	/**
	 * 
	 * @param username
	 * @param password
	 * @param lastlogin
	 * @return
	 */
	public int logout(String username, String password, String lastlogin) {
		LOGGER.trace("inside logout");
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL);
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
