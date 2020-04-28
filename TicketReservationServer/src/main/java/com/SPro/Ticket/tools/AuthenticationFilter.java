package com.SPro.Ticket.tools;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.internal.oxm.conversion.Base64;


public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
     
    @Context
    private ResourceInfo resourceInfo;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class);
      

	private PropertiesLoading propertiesLoading = new PropertiesLoading();
	private String authentication_user = propertiesLoading.getProperties("user");
	private String authentication_password = propertiesLoading.getProperties("password");
	
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                         .entity("Access blocked for all users !!").build());
                return;
            }
              
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
              
            //If no authorization information present; block access
            if(null == authorization || authorization.isEmpty())
            {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You cannot access this resource").build());
                return;
            }
              
            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
              
            //Decode username and password
            String usernameAndPassword = new String(Base64.base64Decode(encodedUserPassword.getBytes()));;
  
            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            //Verifying Username and password
            LOGGER.trace(username);
            LOGGER.trace(password);
              
            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  
                //Is user valid?
                if( ! isUserAllowed(username, password, rolesSet))
                {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
                    return;
                }
                try {
                	String user = requestContext.getHeaderString("username");
                	String token = requestContext.getHeaderString("token");
                	LOGGER.trace("Token "+token);

                	// Validate the token
                	if( ! isUserAllowed(username, password, rolesSet, user, token))
                	{
                		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                				.entity("You cannot access this resource").build());
                		return;
                	}
                } catch (Exception e) {
                	LOGGER.error("Error AT header"+e);
                }
            }
        }
    }
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
          
        if(username.equals(authentication_user) && password.equals(authentication_password))
        {
            isAllowed = true;
            LOGGER.trace("User and password authenticated");
        }
        return isAllowed;
    }
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet,final String user, final String token) {
    	boolean isAllowed = false;
         
        if(username.equals(authentication_user) && password.equals(authentication_password))
        {
            String userRole = "ADMIN";

            if(rolesSet.contains(userRole))
            {
            	
            	if( isUserFound(user, token)) {
            		isAllowed = true;
            		LOGGER.error("Token Authenticated");
            	}
            	else {
            		LOGGER.error("Token not Authenticated");
            	}
            }
        }
        return isAllowed;
    }
    private boolean isUserFound(final String user, final String token) {
    	DBConnections dbConnections = new DBConnections();
    	Connection connection = dbConnections.getConnection();
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	 try {   
 	    	String statement = "select userlogin_table_username,"
 	    			+ "userlogin_table_token from userlogin_table "
 	    			+ "where userlogin_table_username=? and userlogin_table_token=?";
 	    	preparedStatement = connection.prepareStatement(statement);
 		    preparedStatement.setString(1, user);
 		    preparedStatement.setString(2, token);
 	    	resultSet = preparedStatement.executeQuery();
 	    	if(resultSet.next()) {
 	    		return true;
 	    	}
    	 } catch(Exception e) {
    		 LOGGER.error("Error at sql "+e);
    	 }
    	 return false;
    }
}
