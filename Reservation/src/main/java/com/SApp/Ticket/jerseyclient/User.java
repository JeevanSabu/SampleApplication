package com.SApp.Ticket.jerseyclient;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.SApp.Ticket.tools.PropertiesLoading;
import com.SApp.Ticket.tools.SessionUtils;

import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
	public UserPojo postUser(String username,String password){
		LOGGER.trace("Inside User postUser method");
		if(null==username||null==password) {
			LOGGER.trace("Username or Password null");
			return null;
		}
		HttpSession session = SessionUtils.getSession();
		LOGGER.trace(session.getId());

		LoginPojo loginPojo = new LoginPojo();
		loginPojo.setUsername(username);
		loginPojo.setPassword(password);

		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL).path("/user");
		Response response =null;
		try {
			response = webTarget.path("/login")
					.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(loginPojo, MediaType.APPLICATION_JSON));
			LOGGER.trace("Status "+response.getStatus());
			if(response.getStatus()==200) {
				UserPojo userPojo = response.readEntity(UserPojo.class);
				return userPojo;
			}
		}catch(Exception e) {
			LOGGER.error("Error:"+e.getMessage());
		}
		LOGGER.trace("Leaving User postUser method");
		return null;
	}
}
