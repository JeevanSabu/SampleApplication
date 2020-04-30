package com.SApp.Ticket.model;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SApp.Ticket.bean.BusSeatsView;
import com.SApp.Ticket.bean.PassengerSeats;
import com.SApp.Ticket.bean.PaymentBean;
import com.SApp.Ticket.bean.Seats;
import com.SApp.Ticket.jerseyclient.Bus;
import com.SApp.Ticket.jerseyclient.SelectedBusClient;

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
	 * To book the bus with details of the passengers
	 * returns payment page
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside Passenger Model getResult method");
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
				boolean isvalidname = ESAPI.validator().isValidInput("name", seats.getPassengerName(), "name", 30, false);
				boolean isvalidgender = ESAPI.validator().isValidInput("gender", seats.getPassengerGender(), "gender", 8, false);
				if(isvalidname==false||isvalidgender==false) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid fields","Fields possess values that are not allowed"));
					return "login";
				}
				price = price+passengerSeats.getPrice();
				availableSeats--;
			}
			paymentBean.setAvailableSeats(availableSeats);
			paymentBean.setPrice(price);	
		} catch(ValidationException ve) {
			LOGGER.error("Error"+ve.getCause());
		} catch(Exception e) {
			LOGGER.error("Error "+e.getMessage());
		}
		LOGGER.trace("result "+result);
		LOGGER.trace("Leaving Passenger Model getResult method...");
		return result;
	}
	/**
	 * Method to navigate back to the seat selection page
	 * @return
	 */
	public String backToBus() {
		LOGGER.trace("Inside backToBus method ");
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
			return "bus";
		} catch(Exception e) {
			LOGGER.error(e.getCause());
		}
		LOGGER.trace("Leaving backtoBus method");
		return "passenger";		
	}
}
