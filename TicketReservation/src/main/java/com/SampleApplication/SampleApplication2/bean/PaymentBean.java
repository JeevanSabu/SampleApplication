package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "paymentBean" , eager = false)
@ViewScoped
public class PaymentBean {
	private long price;
	private int otp;
	private int verifyOtp;
	private long cardNo;
	private int date;
	private int cvv;
	
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
}
