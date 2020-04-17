package com.SampleApplication.SampleApplication2.tools;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig 
{
    public CustomApplication() 
    {
//        packages("com.SampleApplication.SampleApplication2.tools");
//        register(LoggingFilter.class);
 
        //Register Auth Filter here
        register(AuthenticationFilter.class);
    }
}
