package com.SampleApplication.SampleApplication2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.pojo.UserPojo;
import com.SampleApplication.SampleApplication2.tools.DBConnections;

public class LastLoginDao {

	private static final Logger LOGGER = LogManager.getLogger(LastLoginDao.class);

	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param lastlogin
	 * @return
	 */
	public String loguot(String username, String password, String lastlogin) {
		LOGGER.trace("inside LastLoginDao "+lastlogin);
		UserPojo userPojo = new UserPojo();
		int rowsAffected;
	    PreparedStatement preparedStatement = null;
	    String statement = "update userlogin_table "
	    		+ "set userlogin_table_lastlogin=? "
	    		+ "where userlogin_table_username=? and userlogin_table_password=?";

    	try {
			preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setString(1, lastlogin);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected>0) {
				return "added";
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return statement;
	}

}
