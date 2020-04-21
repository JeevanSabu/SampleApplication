package com.SampleApplication.SampleApplication2.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.PassengerSeats;
import com.SampleApplication.SampleApplication2.bean.PaymentBean;
import com.SampleApplication.SampleApplication2.bean.Seats;

@ManagedBean(name="passengerModel" , eager=true)
@ViewScoped
public class PassengerModel {
	private static final Logger LOGGER = LogManager.getLogger(PassengerModel.class);
	private String result="payment";
	private long price = 0;
	FacesContext context = FacesContext.getCurrentInstance();
	PassengerSeats passengerSeats = (PassengerSeats) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{passengerSeats}", PassengerSeats.class)
			.getValue(context.getELContext());
	PaymentBean paymentBean = (PaymentBean) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{paymentBean}", PaymentBean.class)
			.getValue(context.getELContext());
	/**
	 * To book the bus with details of the passsengers
	 * returns payment page
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside Passenger Model");
		if(null==passengerSeats) {
			return "passenger";
		}
		else {
			paymentBean.setBusId(passengerSeats.getBusId());
			paymentBean.setBusName(passengerSeats.getBusName());
			paymentBean.setSeats(passengerSeats.getSeats());
		}
		try {
			List<Seats> seatlist = passengerSeats.getSeats();
			LOGGER.trace("size "+passengerSeats.getSeats().size());
			for(Seats seats:seatlist) {
				price = price+passengerSeats.getPrice();
				paymentBean.setAvailableSeats(passengerSeats.getAvailableSeats()-1);
			}
			paymentBean.setPrice(price);	
		} catch(Exception e) {
			LOGGER.error("Error "+e.getMessage());
		}
		LOGGER.trace("result "+result);
		LOGGER.trace("Leaving Passenger Model...");
		return result;
	}

}
