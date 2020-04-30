package com.SApp.Ticket.jerseyclient;

import javax.servlet.http.HttpSession;
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

import com.SApp.Ticket.tools.PropertiesLoading;
import com.SApp.Ticket.tools.SessionUtils;

public class LastLoginClient {
	private static final Logger LOGGER = LogManager.getLogger(LastLoginClient.class);
	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String BASE_URL = propertiesLoading.getProperties("resturl");
	private String handshake_user = propertiesLoading.getProperties("user");
	private String handshake_password = propertiesLoading.getProperties("password");

	/**
	 * 	Client method to save the lastlogin time
	 *  on database on logging out
	 * @param username
	 * @param password
	 * @return
	 */
	public int logout(String username, String password) {
		LOGGER.trace("inside logout");

		HttpSession session = SessionUtils.getSession();
		
		LoginPojo loginPojo = new LoginPojo();
		loginPojo.setUsername(username);
		loginPojo.setPassword(password);
		
		ClientConfig clientConfig = new ClientConfig();	
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(handshake_user, handshake_password);
	    clientConfig.register( feature) ;
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(BASE_URL);
		Response response =null;
		response = webTarget.path("/logout")
				.request(MediaType.APPLICATION_JSON)
			    .header("username",session.getAttribute("username"))
			    .header("token",session.getAttribute("token"))
				.post(Entity.entity(loginPojo, MediaType.APPLICATION_JSON));
		LOGGER.trace("Status "+response.getStatus());
		LOGGER.trace("Leaving logout...");
		return response.getStatus();
	}
}
