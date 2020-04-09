package com.SampleApplication.SampleApplication2.jerseyserver;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.dao.HomeDao;
import com.SampleApplication.SampleApplication2.pojo.BusView;

@Path("/home")
public class HomeService {
	private static final Logger LOGGER = LogManager.getLogger(HomeService.class);
	
	private HomeDao homeDao = new HomeDao();
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBus(@QueryParam("source") String source,@QueryParam("destination") String destination,@QueryParam("date") Date date) {
		LOGGER.trace("Query param "+date);
		try {
			BusView busView = homeDao.getBuses(source,destination,date);
			LOGGER.trace("BusView "+busView.getBuses().get(1).getName());
			return Response
	                .ok()
	                .entity(busView)
	                .build();
		} catch(Exception e) {
			LOGGER.error("Error "+e.getMessage());
			return null;
		}
	}
}
