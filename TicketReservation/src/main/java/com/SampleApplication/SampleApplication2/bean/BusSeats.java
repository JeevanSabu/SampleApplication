package com.SampleApplication.SampleApplication2.bean;

public class BusSeats {
	private String seatNo;
	private String status;
	public BusSeats() {
		
	}
	public BusSeats(String seatNo, String status) {
		this.seatNo = seatNo;
		this.status = status;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
