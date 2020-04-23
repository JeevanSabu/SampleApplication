package com.SampleApplication.SampleApplication2.pojo;

public class Bus {
	private String id;
	private String name;
	private String runningtime;
	private int price;
	private int availableseats;

	public Bus() {}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param runningtime
	 * @param price
	 * @param availableseats
	 */
	public Bus(String id, String name, String runningtime, int price, int availableseats) {
		this.id = id;
		this.name = name;
		this.runningtime = runningtime;
		this.price = price;
		this.availableseats = availableseats;
	}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @param availableseats
	 */
	public Bus(String id, String name, int price, int availableseats) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.availableseats = availableseats;
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
	public String getRunningtime() {
		return runningtime;
	}
	/**
	 * 
	 * @param runningtime
	 */
	public void setRunningtime(String runningtime) {
		this.runningtime = runningtime;
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
}
