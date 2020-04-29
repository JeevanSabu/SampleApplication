package com.SApp.Ticket.bean;

public class BusSeats {
	private String seatNo;
	private String status;
	
	public BusSeats() {
		
	}
	/**
	 * 
	 * @param seatNo
	 * @param status
	 */
	public BusSeats(String seatNo, String status) {
		this.seatNo = seatNo;
		this.status = status;
	}
	/**
	 * 
	 * @return
	 */
	public String getSeatNo() {
		return seatNo;
	}
	/**
	 * 
	 * @param seatNo
	 */
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
