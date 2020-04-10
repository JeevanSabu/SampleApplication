package com.SampleApplication.SampleApplication2.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertiesLoading {

	private static final Logger LOGGER = LogManager.getLogger(PropertiesLoading.class);

	public Properties getProperties() {
		try  {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("component.properties");

            Properties properties = new Properties();

            // load a properties file
            properties.load(inputStream);


        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage());
        }
		return null;
	}
	
}
