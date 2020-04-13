package com.SampleApplication.SampleApplication2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.pojo.BookingListPojo;
import com.SampleApplication.SampleApplication2.pojo.BookingsPojo;
import com.SampleApplication.SampleApplication2.tools.DBConnections;

public class BookingListDao {

	private static final Logger LOGGER = LogManager.getLogger(BookingListDao.class);
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	
	public BookingListPojo getBookingList(String username) {
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
	    
	    return bookingListPojo;
	}

}
