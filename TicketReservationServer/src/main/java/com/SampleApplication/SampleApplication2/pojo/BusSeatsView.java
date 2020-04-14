package com.SampleApplication.SampleApplication2.pojo;

import java.util.List;

public class BusSeatsView {
	private List<BusSeats> busSeats;
	private String busName;
	private int busId;

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

}
