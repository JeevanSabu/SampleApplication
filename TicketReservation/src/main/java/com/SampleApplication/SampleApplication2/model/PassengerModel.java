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
	private int price = 0;
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
			LOGGER.trace("passengerSeats null");
			return "passenger";
		}
		else if(0==passengerSeats.getBusId()) {
			LOGGER.trace("busid of passengerSeats null");
			return "passenger";
		}
		else if(null==passengerSeats.getBusName()) {
			LOGGER.trace("busname of passengerSeats null");
			return "passenger";
		}
		else if(0==passengerSeats.getPrice()) {
			LOGGER.trace("price field of passengerSeats null");
			return "passenger";
		}
		else if(0==passengerSeats.getAvailableSeats()) {
			LOGGER.trace("available seats of passengerSeats null");
			return "passenger";
		}
		else if(null==passengerSeats.getSeats()) {
			LOGGER.trace("One or more fields of passengerSeats null");
			return "passenger";
		}
		else if(0==passengerSeats.getBusId()||
				null==passengerSeats.getBusName()||
				0==passengerSeats.getPrice()||
				0==passengerSeats.getAvailableSeats()||
				null==passengerSeats.getSeats()) {
			LOGGER.trace("One or more fields of passengerSeats null");
			return "passenger";
		}
		else {
			paymentBean.setBusId(passengerSeats.getBusId());
			paymentBean.setBusName(passengerSeats.getBusName());
			paymentBean.setSeats(passengerSeats.getSeats());
			paymentBean.setAvailableSeats(passengerSeats.getAvailableSeats());
		}
		try {
			List<Seats> seatlist = passengerSeats.getSeats();
			int availableSeats = passengerSeats.getAvailableSeats();
			LOGGER.trace("size "+passengerSeats.getSeats().size());
			for(Seats seats:seatlist) {
				price = price+passengerSeats.getPrice();
				availableSeats--;
			}
			paymentBean.setAvailableSeats(availableSeats);
			paymentBean.setPrice(price);	
		} catch(Exception e) {
			LOGGER.error("Error "+e.getMessage());
		}
		LOGGER.trace("result "+result);
		LOGGER.trace("Leaving Passenger Model...");
		return result;
	}

}
