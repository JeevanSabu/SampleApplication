package com.SampleApplication.SampleApplication2.jerseyserver;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.HomeDao;
import com.SampleApplication.SampleApplication2.pojo.BusViewPojo;

@Path("/buslist")
public class HomeService {
	private static final Logger LOGGER = LogManager.getLogger(HomeService.class);
	
	private HomeDao homeDao = new HomeDao();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public BusViewPojo postBuses(@FormParam("source") String source, @FormParam("destination") String destination, @FormParam("date") String date) {
		LOGGER.trace("Inside HomeService postBuses method");
		if(null==source||null==destination||null==date) {
			LOGGER.error("One or more fields null");
			return null;
		}
		LOGGER.trace("From query param "+ source);
		try {
			BusViewPojo busView = homeDao.getBuses(source,destination,date);
			return busView;
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.trace("Leaving HomeService postBuses method");
		return null;
	}
}
