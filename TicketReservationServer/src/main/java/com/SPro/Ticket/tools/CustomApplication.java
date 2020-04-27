package com.SPro.Ticket.tools;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig 
{
	/**
	 * To rergister authentication filter
	 */
    public CustomApplication() 
    {
        packages("com.SPro.Ticket.tools");
//        register(LoggingFilter.class);
 
        //Register Auth Filter here
        register(AuthenticationFilter.class);
    }
}
