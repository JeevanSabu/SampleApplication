package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name="passengerSeats" , eager=true)
@ViewScoped
public class PassengerSeats {
	private int busId;
	private String busName;
	private int price;
	private int availableSeats;
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
	/**
	 * 
	 * @return
	 */
	public int getBusId() {
		return busId;
	}
	/**
	 * 
	 * @param id
	 */
	public void setBusId(int id) {
		this.busId = id;
	}
	/**
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
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
}
