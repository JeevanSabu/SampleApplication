package com.SApp.Ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SApp.Ticket.pojo.BookingListPojo;
import com.SApp.Ticket.pojo.BookingsPojo;
import com.SApp.Ticket.tools.DBConnections;

public class BookingListDao {

	private static final Logger LOGGER = LogManager.getLogger(BookingListDao.class);
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	
	/**
	 * Method to fetch list of bookings from the database
	 * @param username
	 * @return
	 */
	public BookingListPojo getBookingList(String username) {
		LOGGER.trace("Inside getBookingList method");
		if(null==username) {
			LOGGER.error("No User from service");
			return null;
		}
		LOGGER.trace("From arguments "+username);
		
		BookingListPojo bookingListPojo = new BookingListPojo();
		bookingListPojo.setBookingList(new ArrayList<BookingsPojo>());
		ResultSet resultSet = null;
	    PreparedStatement preparedStatement = null;

	    try {   
	    	String statement = "select bookings_table_username,"
	    			+ "bookings_table_busname,"
	    			+ "bookings_table_fromto,"
	    			+ "bookings_table_journeydate,"
	    			+ "bookings_table_bookingtime,"
	    			+ "bookings_table_passengers"
	    			+ " from bookings_table "
	    			+ "where bookings_table_username=?";
	    	preparedStatement = connection.prepareStatement(statement);
		    preparedStatement.setString(1, username);
	    	resultSet = preparedStatement.executeQuery();

	    	while(resultSet.next()) {
	    		bookingListPojo.getBookingList().add(new BookingsPojo(resultSet.getString("bookings_table_username"),
	    				resultSet.getString("bookings_table_busname"),
	    				resultSet.getString("bookings_table_fromto"),
	    				resultSet.getString("bookings_table_journeydate"),
	    				resultSet.getString("bookings_table_bookingtime"),
	    				resultSet.getString("bookings_table_passengers")));
	        }
	    	
	    	LOGGER.trace("From BookingsListPojo "+bookingListPojo.getBookingList());
	    	
	    } catch (SQLException se) {
	    	LOGGER.error(se.getMessage());
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage());
	    }

		LOGGER.trace("Leaving getBookingList method");
	    return bookingListPojo;
	}

}
