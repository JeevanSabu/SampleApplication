package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.SampleApplication.SampleApplication2.jerseyclient.Bus;

@ManagedBean(name="selectedBusBean" , eager=true)
@SessionScoped
public class SelectedBusBean {
	private String id;
	private String name;
	private String time;
	private String price;
	private int availableseats;
	private Bus selectedBus;

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 */
	public Bus getSelectedBus() {
		return selectedBus;
	}
	/**
	 * 
	 * @param selectedBus
	 */
	public void setSelectedBus(Bus selectedBus) {
		this.selectedBus = selectedBus;
	}
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 
	 * @return
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 
	 * @return
	 */
	public int getAvailableseats() {
		return availableseats;
	}
	/**
	 * 
	 * @param availableseats
	 */
	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}
	
}
