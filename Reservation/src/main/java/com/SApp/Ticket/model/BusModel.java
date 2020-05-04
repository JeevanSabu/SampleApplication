package com.SApp.Ticket.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SApp.Ticket.bean.BusBean;
import com.SApp.Ticket.bean.BusSeatsView;
import com.SApp.Ticket.bean.PassengerSeats;
import com.SApp.Ticket.bean.Seats;

@ManagedBean(name = "busModel" , eager=true)
@ViewScoped
public class BusModel {
	private static final Logger LOGGER = LogManager.getLogger(BusModel.class);
	private String result="passenger";
	FacesContext context = FacesContext.getCurrentInstance();
	BusBean busBean = (BusBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{busBean}", BusBean.class)
	              .getValue(context.getELContext());
	PassengerSeats passengerSeats = (PassengerSeats) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{passengerSeats}", PassengerSeats.class)
	              .getValue(context.getELContext());
	BusSeatsView busSeatsView = (BusSeatsView) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{busSeatsView}", BusSeatsView.class)
	              .getValue(context.getELContext());
	/**
	 * method to book selected seats
	 * navigates to passenger details page
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside BusModel getResult method");
		try {
			if(null==busBean||null==busSeatsView) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data error","Field can't be null"));
				return "bus";
			}
			boolean isvalidseats = ESAPI.validator().isValidInput("seats", busBean.getSelectedSeat(), "seats", 50, false);
			if(isvalidseats==false) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data error","Invalid data"));
				return "bus";
			}
			String[] seatsString = busBean.getSelectedSeat().split(",");
			passengerSeats.setBusId(busSeatsView.getBusId());
			passengerSeats.setBusName(busSeatsView.getBusName());
			passengerSeats.setPrice(busSeatsView.getPrice());
			passengerSeats.setAvailableSeats(busSeatsView.getAvailableSeats());
			List<Seats> list = new ArrayList<Seats>();
			for(int i=0;i<seatsString.length;i++) {
				LOGGER.trace("seats "+seatsString[i]);
//				passengerSeats.getSeats().add(new Seats(seatsString[i],"",0,""));
				list.add(new Seats(seatsString[i],"",0,""));
//				list.add(seats);
			}
			passengerSeats.setSeats(list);
		} catch(ValidationException ve) {
			LOGGER.error("Error"+ve.getCause());
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return "bus";
		}
		LOGGER.trace("Leaving BusModel getResult method...");
		return result;
	}

}
