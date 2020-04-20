package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name="busSeatsView" ,eager=true)
@ViewScoped
public class BusSeatsView {
	private List<BusSeats> busSeats;
	private String busName;
	private int busId;
	private int price;

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

}
