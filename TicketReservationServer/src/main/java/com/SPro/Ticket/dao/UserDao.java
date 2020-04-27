package com.SPro.Ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SPro.Ticket.pojo.UserPojo;
import com.SPro.Ticket.tools.DBConnections;

public class UserDao {

	private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
//	private static List<UserPojo> users = new ArrayList<UserPojo>();
//	static {
//		users.add(new UserPojo("Jerin","Jerin@123"));
//		users.add(new UserPojo("Jeeson","Jeeson@123"));
//	}
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserPojo getUser(String username, String password) {
		LOGGER.trace("From arguments "+username);
		
		
//		for(UserPojo userPojo : users) {
//			if(userPojo.getUsername().equals(username)&&userPojo.getPassword().equals(password)){
//				return userPojo;
//			}
//		}
//		return null;

		UserPojo userPojo = new UserPojo();
		ResultSet resultSet = null;
	    PreparedStatement preparedStatement = null;

	    try {   
	    	String statement = "select userlogin_table_username,"
	    			+ "userlogin_table_password,"
	    			+ "userlogin_table_lastlogin from userlogin_table "
	    			+ "where userlogin_table_username=? and userlogin_table_password=?";
	    	preparedStatement = connection.prepareStatement(statement);
		    preparedStatement.setString(1, username);
		    preparedStatement.setString(2, password);
	    	resultSet = preparedStatement.executeQuery();

	    	while(resultSet.next()) {
	    	  userPojo.setUsername(resultSet.getString("userlogin_table_username"));
	    	  userPojo.setPassword(resultSet.getString("userlogin_table_password"));	
	    	  userPojo.setLastlogin(resultSet.getString("userlogin_table_lastlogin"));
	        }
	    	String token = issueToken();
	    	String statement1 = "update userlogin_table set userlogin_table_token=? where userlogin_table_username=? and userlogin_table_password=?";
	    	preparedStatement = connection.prepareStatement(statement1);
		    preparedStatement.setString(1, token);
		    preparedStatement.setString(2, username);
		    preparedStatement.setString(3, password);
		    int rowsAffected = preparedStatement.executeUpdate();
		    LOGGER.trace("Rows Affected "+rowsAffected);
		    userPojo.setAccessToken(token);
		    LOGGER.trace("Access token "+userPojo.getAccessToken());
	    } catch (SQLException e) {
	         LOGGER.error("Table exception "+e.getMessage());
	    }
	    LOGGER.trace("userpojo "+userPojo.getUsername());
	    return userPojo;
	      
	}

	private String issueToken() {
		Random random = new Random();
		String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder returnValue = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            returnValue.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
