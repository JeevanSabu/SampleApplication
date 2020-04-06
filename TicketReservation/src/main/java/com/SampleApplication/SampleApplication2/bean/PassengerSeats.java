package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="passengerSeats" , eager=true)
@SessionScoped
public class PassengerSeats {
	private List<Seats> seats;

	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
}
