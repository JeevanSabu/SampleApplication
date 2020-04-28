package com.SPro.Ticket.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SPro.Ticket.bean.BusSeatsView;
import com.SPro.Ticket.bean.SelectedBusBean;
import com.SPro.Ticket.jerseyclient.SelectedBusClient;

@ManagedBean(name="bookingModel",eager=true)
@ViewScoped
public class BookingModel {
	private static final Logger LOGGER = LogManager.getLogger(BookingModel.class);
	private String result="bus1";

	FacesContext context = FacesContext.getCurrentInstance();
	SelectedBusBean selectedBusBean = (SelectedBusBean) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{selectedBusBean}", SelectedBusBean.class)
			.getValue(context.getELContext());
	BusSeatsView busSeatsView = (BusSeatsView) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{busSeatsView}", BusSeatsView.class)
			.getValue(context.getELContext());      
	private SelectedBusClient selectedBusClient = new SelectedBusClient();
	String bus;
	  
	  //action listener event
	  public void attrListener(ActionEvent event){
	 
		  bus = (String) event.getComponent().getAttributes().get("bus");
		  //use this in method
	  }
	public String getResult() {
		LOGGER.trace("Inside SelectedBusModel.getResult");
//		HttpServletRequest request = ((HttpServletRequest)context.getExternalContext().getRequest());
//		LOGGER.trace(request.getParameter("id"));
//		LOGGER.trace(request.getParameter("name"));
//		LOGGER.trace(request.getParameter("time"));
//		LOGGER.trace(request.getParameter("price"));
//		LOGGER.trace(request.getParameter("avlseats"));
//		if(null==selectedBusBean) {
//			return null;
//		}
		try {			
//			Map<String,String> params = context.getExternalContext().getRequestParameterMap();
//			LOGGER.trace("Booked Bus id "+params.get("busid"));
//			LOGGER.trace("Booked Bus id "+busView.getSelectedBus().getId());
//			LOGGER.trace("Booked Bus id "+selectedBusBean.getSelectedBus().getId());
			
			busSeatsView.setBusId(Integer.parseInt(selectedBusBean.getSelectedBus().getId()));
			busSeatsView.setBusName(selectedBusBean.getSelectedBus().getName());
			busSeatsView.setPrice(selectedBusBean.getSelectedBus().getPrice());
			busSeatsView.setAvailableSeats(selectedBusBean.getSelectedBus().getAvailableseats());
			
//			busSeatsView.setBusSeats(selectedBusClient.getSelectedBus(selectedBusBean.getSelectedBus().getId(),selectedBusBean.getSelectedBus().getName()));
			busSeatsView.setBusSeats(selectedBusClient.postSelectedBus(selectedBusBean.getSelectedBus()));
//			busSeatsView.setBusSeats(selectedBusClient.postSelectedBus(bus));
			
//			busSeatsView.setBusId(Integer.parseInt(selectedBusBean.getId()));
//			busSeatsView.setBusName(selectedBusBean.getName());
//			busSeatsView.setPrice(Integer.parseInt(selectedBusBean.getPrice()));
//			busSeatsView.setBusSeats(selectedBusClient.getSelectedBus(selectedBusBean.getId(),selectedBusBean.getName()));
//			LOGGER.trace("busSeatsView "+busSeatsView.getBusSeats().get(0).getSeatNo());
			result="bus1";
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			result="booking";
		}
		LOGGER.trace("leaving SelectedBusModel.getResult");
		return result;
	}
	public String getBus() {
		LOGGER.trace("Inside SelectedBusModel.getResult");
//		if(null==id) {
//			return null;
//		}
		try {			
////			Map<String,String> params = context.getExternalContext().getRequestParameterMap();
////			LOGGER.trace("Booked Bus id "+params.get("busid"));
////			LOGGER.trace("Booked Bus id "+busView.getSelectedBus().getId());
//			LOGGER.trace("Booked Bus id "+bus1.getId());
//			busSeatsView.setBusId(Integer.parseInt(bus1.getId()));
//			busSeatsView.setBusName(bus1.getName());
//			busSeatsView.setPrice(bus1.getPrice());
//			busSeatsView.setAvailableSeats(bus1.getAvailableseats());
////			busSeatsView.setBusSeats(selectedBusClient.getSelectedBus(bus.getId(),bus.getName()));
//			busSeatsView.setBusSeats(selectedBusClient.postSelectedBus(bus1));
////			busSeatsView.setBusId(Integer.parseInt(selectedBusBean.getId()));
////			busSeatsView.setBusName(selectedBusBean.getName());
////			busSeatsView.setPrice(Integer.parseInt(selectedBusBean.getPrice()));
////			busSeatsView.setBusSeats(selectedBusClient.getSelectedBus(selectedBusBean.getId(),selectedBusBean.getName()));
//			LOGGER.trace("busSeatsView "+busSeatsView.getBusSeats().get(0).getSeatNo());
			result="bus1";
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			result="booking";
		}
		LOGGER.trace("leaving SelectedBusModel.getResult");
		return result;
	}
}
