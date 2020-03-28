package com.SampleApplication.SampleApplication2.tools;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="bus" , eager=true)
@SessionScoped
public class Bus {
	private String name;
	private String runningtime;
	private long price;
	private int availableseats;
	public Bus(){
		
	}
	public Bus(String name , String runningtime , long price , int availableseats){
		this.name = name;
		this.runningtime = runningtime;
		this.price = price;
		this.availableseats = availableseats;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRunningtime() {
		return runningtime;
	}
	public void setRunningtime(String runningtime) {
		this.runningtime = runningtime;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getAvailableseats() {
		return availableseats;
	}
	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}
}
