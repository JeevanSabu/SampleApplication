package com.SApp.Ticket.pojo;

import java.util.List;

public class BusSeatsView {
	private List<BusSeats> busSeats;
	private String busName;
	private int busId;

	/**
	 * 
	 * @return
	 */
	public List<BusSeats> getBusSeats() {
		return busSeats;
	}
	/**
	 * 
	 * @param busSeats
	 */
	public void setBusSeats(List<BusSeats> busSeats) {
		this.busSeats = busSeats;
	}
	/**
	 * 
	 * @return
	 */
	public String getBusName() {
		return busName;
	}
	/**
	 * 
	 * @param busName
	 */
	public void setBusName(String busName) {
		this.busName = busName;
	}
	/**
	 * 
	 * @return
	 */
	public int getBusId() {
		return busId;
	}
	/**
	 * 
	 * @param busId
	 */
	public void setBusId(int busId) {
		this.busId = busId;
	}

}
