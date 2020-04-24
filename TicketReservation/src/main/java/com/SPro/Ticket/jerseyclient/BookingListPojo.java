package com.SPro.Ticket.jerseyclient;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "bookingListPojo" , eager=true)
@SessionScoped
public class BookingListPojo {
	private List<BookingsPojo> bookingList;

	/**
	 * 
	 * @return
	 */
	public List<BookingsPojo> getBookingList() {
		return bookingList;
	}
	/**
	 * 
	 * @param bookingList
	 */
	public void setBookingList(List<BookingsPojo> bookingList) {
		this.bookingList = bookingList;
	}


}
