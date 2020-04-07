package com.SampleApplication.SampleApplication2.pojo;

public class Seats {
	private String seatNo;
	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	public Seats(){
	}
	public Seats(String seatNo) {
		this.seatNo = seatNo;		
	}
	public Seats(String seatNo, String passengerName, int passengerAge, String passengerGender) {
		this.seatNo = seatNo;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerGender = passengerGender;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getPassengerGender() {
		return passengerGender;
	}
	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
}
