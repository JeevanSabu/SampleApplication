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
	 * 
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside Passenger Model");
		try {
			List<Seats> seatlist = passengerSeats.getSeats();
			LOGGER.trace("size "+passengerSeats.getSeats().size());
			for(Seats seats:seatlist) {
				price = price+1200;
			}
			paymentBean.setPrice(price);	
		} catch(Exception e) {
			LOGGER.error("Error "+e.getMessage());
		}
		LOGGER.trace("result "+result);
		return result;
	}

}
