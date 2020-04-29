package com.SApp.Ticket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SApp.Ticket.bean.BookingSuccessfullBean;
import com.SApp.Ticket.bean.BusSeatsView;
import com.SApp.Ticket.bean.PassengerSeats;
import com.SApp.Ticket.bean.PaymentBean;
import com.SApp.Ticket.bean.UserBean;
import com.SApp.Ticket.jerseyclient.BookingClient;
import com.SApp.Ticket.jerseyclient.BookingsPojo;

@ManagedBean( name="paymentModel", eager=true)
@ViewScoped
public class PaymentModel {
	private static final Logger LOGGER = LogManager.getLogger(PaymentModel.class);

	private String result;
	private int otp;

	FacesContext context = FacesContext.getCurrentInstance();
	UserBean userBean = (UserBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext() , "#{userBean}" , UserBean.class)
            .getValue(context.getELContext());
	PaymentBean paymentBean = (PaymentBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext() , "#{paymentBean}" , PaymentBean.class)
            .getValue(context.getELContext());
	PassengerSeats passengerSeats = (PassengerSeats) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{passengerSeats}", PassengerSeats.class)
			.getValue(context.getELContext());
	BusSeatsView busSeatsView = (BusSeatsView) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{busSeatsView}", BusSeatsView.class)
            .getValue(context.getELContext()); 
	BookingSuccessfullBean bookingSuccessfullBean = (BookingSuccessfullBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{bookingSuccessfullBean}", BookingSuccessfullBean.class)
            .getValue(context.getELContext()); 
	
	List<String> seatnos = new ArrayList<String>();
	List<String> passname = new ArrayList<String>();
	List<Integer> passage = new ArrayList<Integer>();
	List<String> passgender = new ArrayList<String>();

	private BookingsPojo bookingsPojo = new BookingsPojo();
	private BookingClient bookingClient = new BookingClient();
	/**
	 * 
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside paymentModel getResult method");
		if(null==paymentBean) {
			LOGGER.trace("PaymentBean is null");
			return "payment";
		}
		if(paymentBean.getOtp()==paymentBean.getVerifyOtp()) {
			LOGGER.trace("Verification Successfull");
		}
		try {
			paymentBean.setSeats(passengerSeats.getSeats());
			bookingsPojo = bookingClient.getBookings(userBean.getUsername(),paymentBean);
			LOGGER.trace("From BookingsPojo "+bookingsPojo.getBusname());
			bookingSuccessfullBean.setUsername(bookingsPojo.getUsername());
			bookingSuccessfullBean.setBusname(bookingsPojo.getBusname());
			bookingSuccessfullBean.setFromto(bookingsPojo.getFromto());
			bookingSuccessfullBean.setBookingtime(bookingsPojo.getBookingtime());
			bookingSuccessfullBean.setPassengers(bookingsPojo.getPassengers());
			bookingSuccessfullBean.setDate(bookingsPojo.getDate());
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		result="bookingsuccessfull";

		LOGGER.trace("Leaving paymentModel getResult method");
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String validate(){
		LOGGER.trace("Inside paymentModel validate method");
		Random random = new Random();
		otp = random.nextInt(900000) + 100000;
		paymentBean.setOtp(otp);
		LOGGER.trace("OTP "+otp);

		LOGGER.trace("Leaving paymentModel validate method...");
		return "payment";
	}
}
