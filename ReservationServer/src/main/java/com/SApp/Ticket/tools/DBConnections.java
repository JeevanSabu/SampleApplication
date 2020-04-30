package com.SApp.Ticket.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnections {
	
	private static final Logger LOGGER = LogManager.getLogger(DBConnections.class);
	
	/**
	 * Method to Establish connection to database
	 * returns connection
	 * @return
	 */
	public Connection getConnection() {
		LOGGER.trace("Inside getConnection method");
	    Connection con = null;
	    String url = "jdbc:mysql://localhost:3306/ticket_reservation";
	    String user = "root";
	    String password = "root";
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			LOGGER.trace("MySQL JDBC Driver Registered!");
			con = DriverManager.getConnection(url, user, password);
			LOGGER.trace("Connection completed");
	    } catch (ClassNotFoundException e) {
			LOGGER.error("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			return null;
	    } catch (SQLException ex) {
	         LOGGER.error(ex.getMessage());
	    }  finally {
	    }
		LOGGER.trace("Laeving getConnection method");
	    return con;
	   }
}
