package com.SPro.Ticket.jerseyserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SPro.Ticket.dao.SelectedBusDao;
import com.SPro.Ticket.pojo.Bus;
import com.SPro.Ticket.pojo.BusSeatsView;

@Path("/selectedbus")
public class SelectedBusService {
	private static final Logger LOGGER = LogManager.getLogger(SelectedBusService.class);
	private SelectedBusDao selectedBusDao = new SelectedBusDao();
	
	@POST
	@Path("/selectedbuspost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BusSeatsView getBookinglist(Bus bus) {
		LOGGER.trace("Inside getBookingList method");
		if(null==bus) {
			LOGGER.error("Bus is null");
			return null;
		}
		boolean isvalidbusname = ESAPI.validator().isValidInput("busname", bus.getName(), "busname", 32, false);
		LOGGER.trace("is valid "+isvalidbusname);
		if(isvalidbusname==false) {
			LOGGER.error("Esapi validation returned false");
			return null;
		}
		LOGGER.trace("From query param "+ bus.getName());
		BusSeatsView busSeatsView = selectedBusDao.getSelectedBus(Integer.parseInt(bus.getId()),bus.getName());
		LOGGER.trace("Leaving getBookingList method");
		return busSeatsView;
	}
}
