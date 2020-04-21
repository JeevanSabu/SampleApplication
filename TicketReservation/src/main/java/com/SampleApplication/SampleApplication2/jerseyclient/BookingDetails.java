package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.List;

import com.SampleApplication.SampleApplication2.bean.Seats;

public class BookingDetails {
	private String username;
	private int busId;
	private String busName;
	private int availableSeats;
	private long price;
	private List<Seats> seats;

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 
	 * @return
	 */
	public long getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(long price) {
		this.price = price;
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
	public int getAvailableSeats() {
		return availableSeats;
	}
	/**
	 * 
	 * @param availableSeats
	 */
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
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
