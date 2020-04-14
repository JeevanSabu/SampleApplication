package com.SampleApplication.SampleApplication2.jerseyserver;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.HomeDao;
import com.SampleApplication.SampleApplication2.pojo.BusViewPojo;

@Path("/buslist")
public class HomeService {
	private static final Logger LOGGER = LogManager.getLogger(HomeService.class);
	
	private HomeDao homeDao = new HomeDao();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BusViewPojo getBuses(@QueryParam("source") String source, @QueryParam("destination") String destination, @QueryParam("date") String date) {
		LOGGER.trace("From query param "+ source);
		try {
			BusViewPojo busView = homeDao.getBuses(source,destination,date);
			return busView;
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}
}
