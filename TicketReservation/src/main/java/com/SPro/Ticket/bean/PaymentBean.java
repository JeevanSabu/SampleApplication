package com.SPro.Ticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "paymentBean" , eager = false)
@ViewScoped
public class PaymentBean {
	private int busId;
	private String busName;
	private int availableSeats;
	private int price;
	private int otp;
	private int verifyOtp;
	private long cardNo;
	private int date;
	private int cvv;
	private List<Seats> seats;
	/**
	 * 
	 * @return
	 */
	public int getOtp() {
		return otp;
	}

	/**
	 * 
	 * @param otp
	 */
	public void setOtp(int otp) {
		this.otp = otp;
	}

	/**
	 * 
	 * @return
	 */
	public long getCardNo() {
		return cardNo;
	}

	/**
	 * 
	 * @param cardNo
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 
	 * @return
	 */
	public int getDate() {
		return date;
	}
	/**
	 * 
	 * @param date
	 */
	public void setDate(int date) {
		this.date = date;
	}
	/**
	 * 
	 * @return
	 */
	public int getCvv() {
		return cvv;
	}
	/**
	 * 
	 * @param cvv
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	/**
	 * 
	 * @return
	 */
	public int getVerifyOtp() {
		return verifyOtp;
	}
	/**
	 * 
	 * @param verifyOtp
	 */
	public void setVerifyOtp(int verifyOtp) {
		this.verifyOtp = verifyOtp;
	}
	/**
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
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
	public List<Seats> getSeats() {
		return seats;
	}
	/**
	 * 
	 * @param seats
	 */
	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
}
