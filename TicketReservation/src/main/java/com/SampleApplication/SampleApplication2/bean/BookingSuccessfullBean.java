package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "bookingSuccessfullBean" , eager =true)
@SessionScoped
public class BookingSuccessfullBean  {
	private String username;
	private String busname;
	private String fromto;
	private String date;
	private String bookingtime;
	private String passengers;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFromto() {
		return fromto;
	}
	public void setFromto(String fromto) {
		this.fromto = fromto;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPassengers() {
		return passengers;
	}
	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}
	public String getBusname() {
		return busname;
	}
	public void setBusname(String busname) {
		this.busname = busname;
	}
	public String getBookingtime() {
		return bookingtime;
	}
	public void setBookingtime(String bookingtime) {
		this.bookingtime = bookingtime;
	}
}