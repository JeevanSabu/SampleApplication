package com.SampleApplication.SampleApplication2.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnections {
	
	private static final Logger LOGGER = LogManager.getLogger(DBConnections.class);
	
	public Connection getConnection() {
	      Connection con = null;
	      String url = "jdbc:mysql://localhost:3307/ticket_reservation";
	      String user = "root";
	      String password = "root";
	      try {
			Class.forName("com.mysql.jdbc.Driver");
			LOGGER.trace("MySQL JDBC Driver Registered!");
	      } catch (ClassNotFoundException e) {
			LOGGER.error("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			return null;
	      }
	      
	      try {
	         con = DriverManager.getConnection(url, user, password);
	         LOGGER.trace("Connection completed");
	      } catch (SQLException ex) {
	         LOGGER.error(ex.getMessage());
	      }
	      
	      finally {
	      }
	      return con;
	   }
}
