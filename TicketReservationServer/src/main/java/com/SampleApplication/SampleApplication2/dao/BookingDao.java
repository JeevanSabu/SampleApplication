package com.SampleApplication.SampleApplication2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.pojo.BookingsPojo;
import com.SampleApplication.SampleApplication2.pojo.PassengerSeats;
import com.SampleApplication.SampleApplication2.pojo.Seats;
import com.SampleApplication.SampleApplication2.tools.DBConnections;

public class BookingDao {

	private static final Logger LOGGER = LogManager.getLogger(BookingDao.class);
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	
	
	public BookingsPojo getBookings(int busid, String username, String[] seatnoslist, String[] passnamelist, String[] passagelist, String[] passgenderlist) {
		LOGGER.trace("From arguments "+busid);
		LOGGER.trace("From arguments "+username);
		LOGGER.trace("From arguments "+seatnoslist[1].trim());
		LOGGER.trace("From arguments "+passnamelist[1].trim());
		LOGGER.trace("From arguments "+Integer.parseInt(passagelist[1].trim()));
		LOGGER.trace("From arguments "+passgenderlist[1].trim());
		PassengerSeats passengerSeats = new PassengerSeats();
		passengerSeats.setSeats(new ArrayList<Seats>());
		BookingsPojo bookingsPojo = new BookingsPojo();
		try {
			
			bookingsPojo.setUsername(username);
			
			for(int i=0;i<seatnoslist.length;i++) {
				passengerSeats.getSeats().add(new Seats(seatnoslist[i].trim(),
						passnamelist[i].trim(),
						Integer.parseInt(passagelist[i].trim()),
						passgenderlist[i].trim()));
			}
		} catch(Exception fe) {
			LOGGER.error("At for "+fe.getMessage());
		}
		LOGGER.trace(passengerSeats.getSeats().get(0).getPassengerName());
		
		ResultSet resultSet = null;
	    PreparedStatement preparedStatement = null;
	    int rowsAffected = 0;
	    String passengers = "";
		try {
		    String statement1 = "select buslist_table_slno,"
					+ "buslist_table_name,"
					+ "buslist_table_sourcetime,"
					+ "buslist_table_destinationtime,"
					+ "buslist_table_source,"
					+ "buslist_table_destination from buslist_table where buslist_table_slno=?";
			preparedStatement = connection.prepareStatement(statement1);
			preparedStatement.setInt(1, busid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				bookingsPojo.setBusname(resultSet.getString("buslist_table_name"));
				bookingsPojo.setFromto(resultSet.getString("buslist_table_source")+"-"+resultSet.getString("buslist_table_destination"));
				bookingsPojo.setDate(resultSet.getString("buslist_table_sourcetime")+"-"+resultSet.getString("buslist_table_destinationtime"));
			}
		} catch (SQLException e1) {
			LOGGER.error("ERROR at buslist_table "+e1.getMessage());
		}
		
	    for(Seats seats:passengerSeats.getSeats()) {
	    	passengers = passengers
	    			+seats.getPassengerName()
	    			+"("
	    			+seats.getSeatNo()
	    			+") ";
	    	
	    	try {   
	    		String statement = "insert into busseats_table (busseats_table_busid,"
	    				+ "busseats_table_seatno,"
	    				+ "busseats_table_passangername,"
	    				+ "busseats_table_passengerage,"
	    				+ "busseats_table_passengergender) values(?,?,?,?,?)";
	    		preparedStatement = connection.prepareStatement(statement);
	    		preparedStatement.setInt(1, busid);
	    		preparedStatement.setString(2, seats.getSeatNo());
	    		preparedStatement.setString(3, seats.getPassengerName());
	    		preparedStatement.setInt(4, seats.getPassengerAge());
	    		preparedStatement.setString(5, seats.getPassengerGender());
	    		rowsAffected = preparedStatement.executeUpdate();
	    		
	    		LOGGER.trace("Rows Affected for busseats_table"+rowsAffected);
	    		
	    		
	    	} catch (SQLException e) {
	    		LOGGER.error("Table exception for busseats_table"+e.getMessage());
	    	}
	    }
	
	    bookingsPojo.setPassengers(passengers);
	    	    
    	try {
    		String statement2 = "insert into bookings_table (bookings_table_username,"
    				+ "bookings_table_fromto,"
    				+ "bookings_table_journeydate,"
    				+ "bookings_table_passengers,"
    				+ "bookings_table_busname) values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(statement2);
	    	preparedStatement.setString(1, username);
	    	preparedStatement.setString(2, bookingsPojo.getFromto());
	    	preparedStatement.setString(3, bookingsPojo.getDate());
	    	preparedStatement.setString(4, bookingsPojo.getPassengers());
	    	preparedStatement.setString(5, bookingsPojo.getBusname());
	    	rowsAffected = preparedStatement.executeUpdate();
	    	
	    	LOGGER.trace("Rows Affected for bookings_table "+rowsAffected);
		} catch (SQLException e) {
			LOGGER.error("Exception at bookings_table"+e.getMessage());
		}
    	
		LOGGER.trace("Rows Affected "+rowsAffected);
		
	    return bookingsPojo;
	}
	
}
