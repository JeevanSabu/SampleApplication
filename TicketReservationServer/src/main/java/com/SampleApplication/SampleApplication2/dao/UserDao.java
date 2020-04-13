package com.SampleApplication.SampleApplication2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.pojo.UserPojo;
import com.SampleApplication.SampleApplication2.tools.DBConnections;

public class UserDao {

	private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
//	private static List<UserPojo> users = new ArrayList<UserPojo>();
//	static {
//		users.add(new UserPojo("Jerin","Jerin@123"));
//		users.add(new UserPojo("Jeeson","Jeeson@123"));
//	}
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	@SuppressWarnings("null")
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
	    			+ "userlogin_table_password from userlogin_table "
	    			+ "where userlogin_table_username=? and userlogin_table_password=?";
	    	preparedStatement = connection.prepareStatement(statement);
		    preparedStatement.setString(1, username);
		    preparedStatement.setString(2, password);
	    	resultSet = preparedStatement.executeQuery();

	    	while(resultSet.next()) {
	    	  userPojo.setUsername(resultSet.getString("userlogin_table_username"));
	    	  userPojo.setPassword(resultSet.getString("userlogin_table_password"));			
	        }
	    	
//	    	String statement1 = "insert into bookings_table (bookings_table_username,bookings_table_busname,"
//	    			+ "bookings_table_fromto,bookings_table_journeydate,bookings_table_passengers) "
//	    			+ "values('Jeeson','Kallada','Chennai-Kerala','25 April 2020 18:30','Jeeson (17,male)')";
//	    	preparedStatement = connection.prepareStatement(statement1);
//	    	int row = preparedStatement.executeUpdate();
//	    	LOGGER.trace(row);
//	    	
	    } catch (SQLException e) {
	         LOGGER.error("Table exception "+e.getMessage());
	    }
	    LOGGER.trace("userpojo "+userPojo.getUsername());
	    return userPojo;
	      
	}
//	public UserPojo getList() {
//
//		for(UserPojo userPojo : users) {
//			if(userPojo.getUsername().equals("Jerin")&&userPojo.getPassword().equals("Jerin@123")){
//				return userPojo;
//			}
//		}
//		return null;
//	}

}
