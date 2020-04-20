package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name="passengerSeats" , eager=true)
@ViewScoped
public class PassengerSeats {
	private List<Seats> seats;

	/**
	 * 
	 * @return
	 */
	public List<Seats> getSeats() {
		return seats;
	}

	/**
	 * 
	 * @param seats
	 */
	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
}
