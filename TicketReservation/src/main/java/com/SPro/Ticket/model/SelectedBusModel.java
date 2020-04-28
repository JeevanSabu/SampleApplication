package com.SPro.Ticket.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.SPro.Ticket.bean.BusSeatsView;
import com.SPro.Ticket.bean.SelectedBusBean;
import com.SPro.Ticket.jerseyclient.Bus;
import com.SPro.Ticket.jerseyclient.SelectedBusClient;

@ManagedBean(name="selectedBusModel", eager=true)
@ViewScoped
public class SelectedBusModel {
	private static final Logger LOGGER = LogManager.getLogger(SelectedBusModel.class);
	private String result="bus1";
	FacesContext context = FacesContext.getCurrentInstance();
	SelectedBusBean selectedBusBean = (SelectedBusBean) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{selectedBusBean}", SelectedBusBean.class)
			.getValue(context.getELContext());
	BusSeatsView busSeatsView = (BusSeatsView) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{busSeatsView}", BusSeatsView.class)
			.getValue(context.getELContext());      
	private SelectedBusClient selectedBusClient = new SelectedBusClient();

	/**
	 * 
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside SelectedBusModel.getResult");
		if(null==selectedBusBean) {
			LOGGER.error("selected bus null");
			return "booking";
		}
		try {			
			busSeatsView.setBusId(Integer.parseInt(selectedBusBean.getId()));
			busSeatsView.setBusName(selectedBusBean.getName());
			busSeatsView.setPrice(Integer.parseInt(selectedBusBean.getPrice()));
			busSeatsView.setAvailableSeats(selectedBusBean.getAvailableseats());
			
			busSeatsView.setBusSeats(selectedBusClient.postSelectedBus(new Bus(selectedBusBean.getId() , selectedBusBean.getName(), selectedBusBean.getTime(), Integer.parseInt(selectedBusBean.getPrice()), selectedBusBean.getAvailableseats())));
			
//			LOGGER.trace("busSeatsView "+busSeatsView.getBusSeats().get(0).getSeatNo());
			result="bus1";
		} catch(Exception e) {
			LOGGER.error(e.getCause());
			result="booking";
		}
		LOGGER.trace("leaving SelectedBusModel.getResult");
		return result;
	}

}
