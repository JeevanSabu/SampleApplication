package com.SPro.Ticket.pojo;

public class BookingsPojo {
	private String username;
	private String busname;
	private String fromto;
	private String date;
	private String bookingtime;
	private String passengers;
	
	public BookingsPojo() {}
	/**
	 * 
	 * @param username
	 * @param busname
	 * @param fromto
	 * @param date
	 * @param bookingtime
	 * @param passengers
	 */
	public BookingsPojo(String username, String busname, String fromto, String date, String bookingtime, String passengers) {
		this.username = username;
		this.busname = busname;
		this.fromto = fromto;
		this.date = date;
		this.bookingtime = bookingtime;
		this.passengers = passengers;
	}
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
