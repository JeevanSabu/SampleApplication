package com.SApp.Ticket.jerseyclient;

import java.util.List;

public class BookingDetails {
	private String username;
	private int busId;
	private String busName;
	private int availableSeats;
	private long price;
	private List<String> seatnos;
	private List<String> passname;
	private List<Integer> passage;
	private List<String> passgender;
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
	public long getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(long price) {
		this.price = price;
	}
	/**
	 * 
	 * @return
	 */
	public int getBusId() {
		return busId;
	}
	/**
	 * 
	 * @param busId
	 */
	public void setBusId(int busId) {
		this.busId = busId;
	}
	/**
	 * 
	 * @return
	 */
	public String getBusName() {
		return busName;
	}
	/**
	 * 
	 * @param busName
	 */
	public void setBusName(String busName) {
		this.busName = busName;
	}
	/**
	 * 
	 * @return
	 */
	public int getAvailableSeats() {
		return availableSeats;
	}
	/**
	 * 
	 * @param availableSeats
	 */
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getSeatnos() {
		return seatnos;
	}
	/**
	 * 
	 * @param seatnos
	 */
	public void setSeatnos(List<String> seatnos) {
		this.seatnos = seatnos;
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getPassname() {
		return passname;
	}
	/**
	 * 
	 * @param passname
	 */
	public void setPassname(List<String> passname) {
		this.passname = passname;
	}
	/**
	 * 
	 * @return
	 */
	public List<Integer> getPassage() {
		return passage;
	}
	/**
	 * 
	 * @param passage
	 */
	public void setPassage(List<Integer> passage) {
		this.passage = passage;
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getPassgender() {
		return passgender;
	}
	/**
	 * 
	 * @param passgender
	 */
	public void setPassgender(List<String> passgender) {
		this.passgender = passgender;
	}
}
