package com.SampleApplication.SampleApplication2.jerseyserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.SelectedBusDao;
import com.SampleApplication.SampleApplication2.pojo.BusSeatsView;

@Path("/selectedbus")
public class SelectedBusService {
	private static final Logger LOGGER = LogManager.getLogger(SelectedBusService.class);
	private SelectedBusDao selectedBusDao = new SelectedBusDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BusSeatsView getBookinglist(@QueryParam("id") String id, @QueryParam("name") String name) {
		LOGGER.trace("From query param "+ name);
		BusSeatsView busSeatsView = selectedBusDao.getSelectedBus(Integer.parseInt(id),name);
		return busSeatsView;
	}
}
