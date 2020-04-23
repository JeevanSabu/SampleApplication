package com.SampleApplication.SampleApplication2.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BusSeatsView;
import com.SampleApplication.SampleApplication2.bean.PassengerSeats;
import com.SampleApplication.SampleApplication2.bean.PaymentBean;
import com.SampleApplication.SampleApplication2.bean.Seats;
import com.SampleApplication.SampleApplication2.jerseyclient.Bus;
import com.SampleApplication.SampleApplication2.jerseyclient.SelectedBusClient;

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
	BusSeatsView busSeatsView = (BusSeatsView) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{busSeatsView}", BusSeatsView.class)
			.getValue(context.getELContext());      
	private SelectedBusClient selectedBusClient = new SelectedBusClient();
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
	/**
	 * 
	 * @return
	 */
	public String backToBus() {
		LOGGER.trace("Inside backToBooking method ");
		if(null==passengerSeats) {
			LOGGER.error("passengerSeats null");
			return "booking";
		}
		try {			
			busSeatsView.setBusId(passengerSeats.getBusId());
			busSeatsView.setBusName(passengerSeats.getBusName());
			busSeatsView.setPrice(passengerSeats.getPrice());
			busSeatsView.setAvailableSeats(passengerSeats.getAvailableSeats());
			
			busSeatsView.setBusSeats(selectedBusClient.postSelectedBus(new Bus(""+passengerSeats.getBusId() , passengerSeats.getBusName(), passengerSeats.getPrice(), passengerSeats.getAvailableSeats())));
			
	//		LOGGER.trace("busSeatsView "+busSeatsView.getBusSeats().get(0).getSeatNo());
			return "bus1";
		} catch(Exception e) {
			LOGGER.error(e.getCause());
			return "passenger";
		}
		
	}
}
