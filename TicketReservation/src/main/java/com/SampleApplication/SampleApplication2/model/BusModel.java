package com.SampleApplication.SampleApplication2.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;

import com.SampleApplication.SampleApplication2.bean.BusBean;
import com.SampleApplication.SampleApplication2.bean.PassengerSeats;
import com.SampleApplication.SampleApplication2.bean.Seats;

@ManagedBean(name = "busModel" , eager=true)
@SessionScoped
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
	public String getResult() throws ValidationException {
		try {
			boolean isvalidseats = ESAPI.validator().isValidInput("seats", busBean.getSelectedSeat(), "seats", 50, false);
			if(isvalidseats==false) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data error","Invalid data"));
				return "bus1";
			}
		} catch(Exception esapiex) {
			LOGGER.error(esapiex.getMessage());
			return "bus1";
		}
		try {
			String[] seatsString = busBean.getSelectedSeat().split(",");
			LOGGER.trace("seats "+seatsString[0]);
//			PassengerSeats passengerSeats =new PassengerSeats();
//			passengerSeats.setSeats(new ArrayList<Seats>());
			List<Seats> list = new ArrayList<Seats>();
			for(int i=0;i<seatsString.length;i++) {
//				passengerSeats.getSeats().add(new Seats(seatsString[i],"",0,""));
				list.add(new Seats(seatsString[i],"",0,""));
//				list.add(seats);
			}
			passengerSeats.setSeats(list);
			LOGGER.trace(passengerSeats.getSeats().get(1).getSeatNo());
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return "bus1";
		}
		return result;
	}

}
