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

import com.SampleApplication.SampleApplication2.pojo.Bus;
import com.SampleApplication.SampleApplication2.pojo.BusView;
import com.SampleApplication.SampleApplication2.tools.DBConnections;

public class HomeDao {
	private static final Logger LOGGER = LogManager.getLogger(HomeDao.class);
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();

	public BusView getBuses(String source, String destination, Date date) {
		LOGGER.trace("From arguments "+source);

		BusView busView = new BusView();
		busView.setBuses(new ArrayList<Bus>());
		ResultSet resultSet = null;
	    PreparedStatement preparedStatement = null;

	    try {   
	    	String statement = "select buslist_table_name,buslist_table_sourcetime,buslist_table_destinationtime,buslist_table_source,buslist_table_destination,buslist_table_price,buslist_table_availableseats from buslist_table where buslist_table_source=? and buslist_table_destination=?";
	    	preparedStatement = connection.prepareStatement(statement);
		    preparedStatement.setString(1, source);
		    preparedStatement.setString(2, destination);
	    	resultSet = preparedStatement.executeQuery();

	    	while(resultSet.next()) {
//	    		Bus bus = new Bus();
//	    		bus.setName(resultSet.getString("buslist_table_name"));
//	    		bus.setRunningtime(resultSet.getString("buslist_table_sourcetime")+"-"+resultSet.getString("buslist_table_destinationtime"));
//	    		bus.setPrice(resultSet.getInt("buslist_table_price"));
//	    		bus.setAvailableseats(resultSet.getInt("buslist_table_availableseats"));
//	    		busView.getBuses().add(bus);
	    		busView.getBuses().add(new Bus(resultSet.getString("buslist_table_name"),
	    				resultSet.getString("buslist_table_sourcetime")+"-"+resultSet.getString("buslist_table_destinationtime"),
	    				resultSet.getInt("buslist_table_price"),
	    				resultSet.getInt("buslist_table_availableseats")));
	    	}
	    } catch (SQLException e) {
	         LOGGER.error("Table exception "+e.getMessage());
	    } catch (Exception ex) {
	    	LOGGER.error("Table exception "+ex.getMessage());
	    }
	    return busView;
	}

}
