package com.SPro.Ticket.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "bookingSuccessfullBean" , eager =true)
@ViewScoped
public class BookingSuccessfullBean  {
	private String username;
	private String busname;
	private String fromto;
	private String date;
	private String bookingtime;
	private String passengers;
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
	public String getFromto() {
		return fromto;
	}
	/**
	 * 
	 * @param fromto
	 */
	public void setFromto(String fromto) {
		this.fromto = fromto;
	}
	/**
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassengers() {
		return passengers;
	}
	/**
	 * 
	 * @param passengers
	 */
	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}
	/**
	 * 
	 * @return
	 */
	public String getBusname() {
		return busname;
	}
	/**
	 * 
	 * @param busname
	 */
	public void setBusname(String busname) {
		this.busname = busname;
	}
	/**
	 * 
	 * @return
	 */
	public String getBookingtime() {
		return bookingtime;
	}
	/**
	 * 
	 * @param bookingtime
	 */
	public void setBookingtime(String bookingtime) {
		this.bookingtime = bookingtime;
	}
}