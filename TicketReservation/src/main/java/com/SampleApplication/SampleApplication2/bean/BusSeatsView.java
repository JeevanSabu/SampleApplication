package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="busSeatsView" ,eager=true)
@SessionScoped
public class BusSeatsView {
	private List<BusSeats> busSeats;

	public List<BusSeats> getBusSeats() {
		return busSeats;
	}

	public void setBusSeats(List<BusSeats> busSeats) {
		this.busSeats = busSeats;
	}
}
