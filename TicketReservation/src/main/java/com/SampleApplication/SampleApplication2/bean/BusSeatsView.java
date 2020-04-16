package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="busSeatsView" ,eager=true)
@SessionScoped
public class BusSeatsView {
	private List<BusSeats> busSeats;
	private String busName;
	private int busId;
	private int price;

	public List<BusSeats> getBusSeats() {
		return busSeats;
	}

	public void setBusSeats(List<BusSeats> busSeats) {
		this.busSeats = busSeats;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
