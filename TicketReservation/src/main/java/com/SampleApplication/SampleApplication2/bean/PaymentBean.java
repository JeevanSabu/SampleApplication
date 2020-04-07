package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "paymentBean" , eager = false)
@SessionScoped
public class PaymentBean {
	private long price;
	private int otp;
	private int verifyOtp;
	private long cardNo;
	private int date;
	private int cvv;
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getVerifyOtp() {
		return verifyOtp;
	}

	public void setVerifyOtp(int verifyOtp) {
		this.verifyOtp = verifyOtp;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
