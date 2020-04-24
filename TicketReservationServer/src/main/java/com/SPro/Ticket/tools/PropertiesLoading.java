package com.SPro.Ticket.tools;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertiesLoading {

	private static final Logger LOGGER = LogManager.getLogger(PropertiesLoading.class);

	/**
	 * 
	 * @param prop
	 * @return
	 */
	public String getProperties(String prop) {
		try  {
			InputStream inputStream = PropertiesLoading.class.getClassLoader().getResourceAsStream("handshake.properties");

            Properties properties = new Properties();

            // load a properties file
            properties.load(inputStream);
            return properties.getProperty(prop);

        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage());
        	return null;
        }
	}
	
}
