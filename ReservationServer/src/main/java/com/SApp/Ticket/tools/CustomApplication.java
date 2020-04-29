package com.SApp.Ticket.tools;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig 
{
	/**
	 * To register authentication filter
	 */
    public CustomApplication() 
    {
        packages("com.SApp.Ticket.tools");
//        register(LoggingFilter.class);
 
        //Register Auth Filter here
        register(AuthenticationFilter.class);
    }
}
