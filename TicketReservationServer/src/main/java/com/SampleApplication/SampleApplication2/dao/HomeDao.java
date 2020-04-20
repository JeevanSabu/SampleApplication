package com.SampleApplication.SampleApplication2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;

import com.SampleApplication.SampleApplication2.pojo.Bus;
import com.SampleApplication.SampleApplication2.pojo.BusViewPojo;
import com.SampleApplication.SampleApplication2.tools.DBConnections;

public class HomeDao {

	private static final Logger LOGGER = LogManager.getLogger(HomeDao.class);
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param date
	 * @return
	 */
	public BusViewPojo getBuses(String source, String destination, String date) {

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
	    		+ "buslist_table_price from buslist_table where buslist_table_source=? and buslist_table_destination=? and buslist_table_date=?";
    	try {
			preparedStatement = connection.prepareStatement(statement);
		    preparedStatement.setString(1, source);
		    preparedStatement.setString(2, destination);
		    preparedStatement.setString(3, date);
	    	resultSet = preparedStatement.executeQuery();
	    	while(resultSet.next()) {
//	    		Bus bus = new Bus();
	    		busView.getBuses().add(new Bus(resultSet.getString("buslist_table_slno"),
	    				resultSet.getString("buslist_table_name"),
	    				resultSet.getString("buslist_table_sourcetime"),
	    				resultSet.getInt("buslist_table_price"),
	    				resultSet.getInt("buslist_table_availableseats")));
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
    	return busView;
	}

}
