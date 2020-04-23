package com.SampleApplication.SampleApplication2.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="homeBean",eager=true)
@ViewScoped
public class HomeBean {
	private String source;
	private String destination;
	private Date date;
	private Date minDate = new Date();
    private Date maxDate = new Date(minDate.getTime() + (5 * 24 * 60 * 60 * 1000));
	/**
	 * 
	 * @return
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 
	 * @return
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * 
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 
	 * @return
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 
	 * @return
	 */
	public Date getMinDate() {
		return minDate;
	}
	/**
	 * 
	 * @param minDate
	 */
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
}
