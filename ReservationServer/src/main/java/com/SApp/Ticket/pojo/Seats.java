package com.SApp.Ticket.pojo;

public class Seats {
	private String seatNo;
	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	
	public Seats(){
	}
	/**
	 * 
	 * @param seatNo
	 */
	public Seats(String seatNo) {
		this.seatNo = seatNo;		
	}
	/**
	 * 
	 * @param seatNo
	 * @param passengerName
	 * @param passengerAge
	 * @param passengerGender
	 */
	public Seats(String seatNo, String passengerName, int passengerAge, String passengerGender) {
		this.seatNo = seatNo;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerGender = passengerGender;
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
	public String getPassengerName() {
		return passengerName;
	}
	/**
	 * 
	 * @param passengerName
	 */
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassengerGender() {
		return passengerGender;
	}
	/**
	 * 
	 * @param passengerGender
	 */
	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}
	/**
	 * 
	 * @return
	 */
	public int getPassengerAge() {
		return passengerAge;
	}
	/**
	 * 
	 * @param passengerAge
	 */
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	
}
