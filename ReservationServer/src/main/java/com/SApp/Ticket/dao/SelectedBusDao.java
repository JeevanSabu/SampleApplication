package com.SApp.Ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SApp.Ticket.pojo.BusSeats;
import com.SApp.Ticket.pojo.BusSeatsView;
import com.SApp.Ticket.tools.DBConnections;

public class SelectedBusDao {
	private static final Logger LOGGER = LogManager.getLogger(SelectedBusDao.class);
	
	DBConnections dbConnections = new DBConnections();
	Connection connection = dbConnections.getConnection();
	
	/**
	 * Method to fetch the details of
	 * selected bus from the database
	 * @param id
	 * @param name
	 * @return
	 */
	public BusSeatsView getSelectedBus(int id, String name) {
		LOGGER.trace("Inside getSelectedBus method");
		if(null==name) {
			LOGGER.error("One or more fileds null");
			return null;
		}
		LOGGER.trace("From arguments "+name);

		BusSeatsView busSeatsView = new BusSeatsView();
		busSeatsView.setBusName(name);
		busSeatsView.setBusId(id);
		busSeatsView.setBusSeats(new ArrayList<BusSeats>());
		
		for(int i=1;i<=28;i++) {
			busSeatsView.getBusSeats().add(new BusSeats("s"+i, "not-selected"));
		}
		ResultSet resultSet = null;
	    PreparedStatement preparedStatement = null;
	    String statement = "select busseats_table_busid,"
	    		+ "busseats_table_seatno from busseats_table where busseats_table_busid=?";
	    try {
	    	preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setInt(1, id);
	    	resultSet = preparedStatement.executeQuery();
//	    	if(resultSet.next()) {
//	    	}
	    	while(resultSet.next()) {
	    		for(BusSeats busSeats:busSeatsView.getBusSeats()) {
	    			if(busSeats.getSeatNo().equals(resultSet.getString("busseats_table_seatno"))) {
	    				busSeats.setStatus("selected");
	    			}
	    		}
	    	}
		} catch (SQLException e) {
			LOGGER.error("Sql Exception"+e.getMessage());
//			return null;
		}
		LOGGER.trace("Leaving getSelectedBus method");
	    return busSeatsView;
	}

}
