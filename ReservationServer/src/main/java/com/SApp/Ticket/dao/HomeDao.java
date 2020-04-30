package com.SApp.Ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SApp.Ticket.pojo.Bus;
import com.SApp.Ticket.pojo.BusViewPojo;
import com.SApp.Ticket.tools.DBConnections;

public class HomeDao {

	private static final Logger LOGGER = LogManager.getLogger(HomeDao.class);
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();

	/**
	 * Method to fetch buses from database on specified date 
	 * form and to 
	 * specified source and location
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 */
	public BusViewPojo getBuses(String source, String destination, String date) {
		LOGGER.trace("Inside getBuses method");
		if(null==source||null==destination||null==date) {
			LOGGER.error("one or more fields none");
			return null;
		}
		LOGGER.trace("From arguments "+date);
		BusViewPojo busView = new BusViewPojo();
		busView.setBuses(new ArrayList<Bus>());
//		List <Bus> list = new ArrayList<Bus>();
		ResultSet resultSet = null;
	    PreparedStatement preparedStatement = null;
	    String statement = "select buslist_table_slno,"
	    		+ "buslist_table_name,"
	    		+ "buslist_table_sourcetime,"
	    		+ "buslist_table_destinationtime,"
	    		+ "buslist_table_availableseats,"
	    		+ "buslist_table_source,"
	    		+ "buslist_table_destination,"
	    		+ "buslist_table_price,"
	    		+ "buslist_table_rating from buslist_table where buslist_table_source=? and buslist_table_destination=? and buslist_table_date=?";
    	try {
			preparedStatement = connection.prepareStatement(statement);
		    preparedStatement.setString(1, source);
		    preparedStatement.setString(2, destination);
		    preparedStatement.setString(3, date);
	    	resultSet = preparedStatement.executeQuery();
	    	if(resultSet.next()) {
		    	while(resultSet.next()) {
		    		busView.getBuses().add(new Bus(resultSet.getString("buslist_table_slno"),
		    				resultSet.getString("buslist_table_name"),
		    				resultSet.getString("buslist_table_sourcetime"),
		    				resultSet.getString("buslist_table_destinationtime"),
		    				resultSet.getInt("buslist_table_price"),
		    				resultSet.getInt("buslist_table_availableseats"),
		    				resultSet.getInt("buslist_table_rating")));
		    	}
	    	}
	    	else {
	    		String tempdate=date;
	    		String selectedSourceDate = null;
	    		String selectedDestinationDate = null;
	    		String[] tempdatearr=date.split(" ");
	    		tempdate = (Integer.parseInt(tempdatearr[0])+1)+"";
	    		for(int i=1;i<tempdatearr.length;i++) {
	    			tempdate += " "+tempdatearr[i];
	    		}
	    		preparedStatement = connection.prepareStatement(statement);
			    preparedStatement.setString(1, source);
			    preparedStatement.setString(2, destination);
			    preparedStatement.setString(3, "25 april 2020");
		    	resultSet = preparedStatement.executeQuery();
		    	while(resultSet.next()) {
		    		selectedSourceDate = resultSet.getString("buslist_table_sourcetime");
		    		selectedSourceDate = selectedSourceDate.replaceAll("25 April 2020", date);
		    		selectedDestinationDate = resultSet.getString("buslist_table_destinationtime");
		    		selectedDestinationDate = selectedDestinationDate.replaceAll("26 April 2020", tempdate);
		    			busView.getBuses().add(new Bus(resultSet.getString("buslist_table_slno"),
		    					resultSet.getString("buslist_table_name"),
		    					selectedSourceDate,
		    					selectedDestinationDate,
		    					resultSet.getInt("buslist_table_price"),
		    					28,
		    					resultSet.getInt("buslist_table_rating")));
		    		}
	    		for(Bus bus:busView.getBuses()) {
	    			String statement2 = "insert into buslist_table "
	    					+ "(buslist_table_name,"
	    		    		+ "buslist_table_sourcetime,"
	    		    		+ "buslist_table_destinationtime,"
	    		    		+ "buslist_table_availableseats,"
	    		    		+ "buslist_table_source,"
	    		    		+ "buslist_table_destination,"
	    		    		+ "buslist_table_price,"
	    		    		+ "buslist_table_date,"
	    		    		+ "buslist_table_rating) values(?,?,?,?,?,?,?,?,?)";

		    		preparedStatement = connection.prepareStatement(statement2);
				    preparedStatement.setString(1, bus.getName());
				    preparedStatement.setString(2, bus.getRunningtime());
				    preparedStatement.setString(3, bus.getReachingtime());
				    preparedStatement.setInt(4, bus.getAvailableseats());
				    preparedStatement.setString(5, source);
				    preparedStatement.setString(6, destination);
				    preparedStatement.setInt(7, bus.getPrice());
				    preparedStatement.setString(8, date);
				    preparedStatement.setInt(9, bus.getRating());
				    
				    int rows = preparedStatement.executeUpdate();
				    LOGGER.trace("rows affected "+rows);
				    
				    
				    busView.setBuses(new ArrayList<Bus>());
//					List <Bus> list = new ArrayList<Bus>();
			    	
					preparedStatement = connection.prepareStatement(statement);
					preparedStatement.setString(1, source);
					preparedStatement.setString(2, destination);
					preparedStatement.setString(3, date);
				   	resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
					 	busView.getBuses().add(new Bus(resultSet.getString("buslist_table_slno"),
					   			resultSet.getString("buslist_table_name"),
					   			resultSet.getString("buslist_table_sourcetime"),
					   			resultSet.getString("buslist_table_destinationtime"),
					   			resultSet.getInt("buslist_table_price"),
					   			resultSet.getInt("buslist_table_availableseats"),
					   			resultSet.getInt("buslist_table_rating")));
					}
	    		}
	    	}
//	    	busView.setBuses(list);
	    	LOGGER.trace("From BusView "+busView.getBuses());
		} catch (SQLException se) {
			LOGGER.error("sql error"+se);
			return null;
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		LOGGER.trace("Leaving getBuses method");
    	return busView;
	}

}
