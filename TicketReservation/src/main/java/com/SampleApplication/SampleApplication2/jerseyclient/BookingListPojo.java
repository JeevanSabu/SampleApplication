package com.SampleApplication.SampleApplication2.jerseyclient;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "bookingListPojo" , eager=true)
@SessionScoped
public class BookingListPojo {
	private List<BookingsPojo> bookingList;

	public List<BookingsPojo> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<BookingsPojo> bookingList) {
		this.bookingList = bookingList;
	}


}
