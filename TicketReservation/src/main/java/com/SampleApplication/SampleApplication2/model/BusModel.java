package com.SampleApplication.SampleApplication2.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	public String getResult() {
		
		String[] seatsString = busBean.getSelectedSeat().split(",");
		LOGGER.trace("seats "+seatsString[0]);
		try {
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
		}
		return result;
	}

}
