package com.SampleApplication.SampleApplication2.pojo;

public class BookingsPojo {
	private String username;
	private String busname;
	private String fromto;
	private String date;
	private String bookingtime;
	private String passengers;
	public BookingsPojo() {}
	public BookingsPojo(String username, String busname, String fromto, String date, String bookingtime, String passengers) {
		this.username = username;
		this.busname = busname;
		this.fromto = fromto;
		this.date = date;
		this.bookingtime = bookingtime;
		this.passengers = passengers;
	}
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
