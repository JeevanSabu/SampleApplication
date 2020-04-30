package com.SApp.Ticket.tools;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertiesLoading {

	private static final Logger LOGGER = LogManager.getLogger(PropertiesLoading.class);

	/**
	 * Method to load properties
	 * @param prop
	 * @return
	 */
	public String getProperties(String prop) {
		LOGGER.trace("Inside getProperties method");
		Properties properties = new Properties();
		try  {
			InputStream inputStream = PropertiesLoading.class.getClassLoader().getResourceAsStream("handshake.properties");

            // load a properties file
            properties.load(inputStream);

        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage());
        	return null;
        }
		LOGGER.trace("Leaving getProperties method");
		return properties.getProperty(prop);
	}
	
}
