package com.SApp.Ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SApp.Ticket.tools.DBConnections;

public class LastLoginDao {

	private static final Logger LOGGER = LogManager.getLogger(LastLoginDao.class);

	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	
	/**
	 * Method to update last login time of the user in the database
	 * @param username
	 * @param password
	 * @param lastlogin
	 * @return
	 */
	public String loguot(String username, String password, String lastlogin) {
		LOGGER.trace("Inside LastLoginDao");
		if(null==username||null==password||null==lastlogin) {
			LOGGER.error("One or more fields null");
			return null;
		}
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
		LOGGER.trace("Leaving LastLoginDao");
		return null;
	}

}
