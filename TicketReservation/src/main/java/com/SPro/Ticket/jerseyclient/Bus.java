package com.SPro.Ticket.jerseyclient;

public class Bus {
	private String id;
	private String name;
	private String runningtime;
	private String reachingtime;
	private int price;
	private int availableseats;
	private int rating;
	
	public Bus(){}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param runningtime
	 * @param price
	 * @param availableseats
	 */
	public Bus(String id, String name , String runningtime , int price , int availableseats){
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
	 * @param runningtime
	 * @param reachingtime
	 * @param price
	 * @param availableseats
	 * @param rating
	 */
	public Bus(String id, String name , String runningtime , String reachingtime , int price , int availableseats , int rating){
		this.id = id;
		this.name = name;
		this.runningtime = runningtime;
		this.reachingtime =reachingtime;
		this.price = price;
		this.availableseats = availableseats;
		this.rating = rating;
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
	/**
	 * 
	 * @return
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * 
	 * @return
	 */
	public String getReachingtime() {
		return reachingtime;
	}
	/**
	 * 
	 * @param reachingtime
	 */
	public void setReachingtime(String reachingtime) {
		this.reachingtime = reachingtime;
	}
}
