package com.SampleApplication.SampleApplication2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BusSeatsView;
import com.SampleApplication.SampleApplication2.bean.PassengerSeats;
import com.SampleApplication.SampleApplication2.bean.PaymentBean;
import com.SampleApplication.SampleApplication2.bean.Seats;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.BookingClient;
import com.SampleApplication.SampleApplication2.jerseyclient.BookingsPojo;

@ManagedBean( name="paymentModel", eager=true)
@SessionScoped
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
	List<String> seatnos = new ArrayList<String>();
	List<String> passname = new ArrayList<String>();
	List<Integer> passage = new ArrayList<Integer>();
	List<String> passgender = new ArrayList<String>();

	private BookingsPojo bookingsPojo = new BookingsPojo();
	private BookingClient bookingClient = new BookingClient();
	public String getResult() {
		if(paymentBean.getOtp()==paymentBean.getVerifyOtp()) {
			LOGGER.trace("Verification Successfull");
		}
		LOGGER.trace("bus id "+busSeatsView.getBusId());
		LOGGER.trace("passengers "+passengerSeats.getSeats().size());
		for(Seats seats:passengerSeats.getSeats()) {
			seatnos.add(seats.getSeatNo());
			passname.add(seats.getPassengerName());
			passage.add(seats.getPassengerAge());
			passgender.add(seats.getPassengerGender());
		}
		LOGGER.trace("list to string "+seatnos.toString());
		try {
			bookingsPojo = bookingClient.getBookings(busSeatsView.getBusId(), userBean.getUsername(), seatnos, passname, passage, passgender);
			LOGGER.trace("From BookingsPojo "+bookingsPojo.getBusname());
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		result="bookingsuccessfull";
		return result;
	}
	
	public String validate(){
		Random random = new Random();
		otp = random.nextInt(900000) + 100000;
		paymentBean.setOtp(otp);
		LOGGER.trace("OTP "+otp);
		return "payment";
	}
}
