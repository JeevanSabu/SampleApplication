package com.SampleApplication.SampleApplication2.pojo;

public class Bus {
	private String id;
	private String name;
	private String runningtime;
	private int price;
	private int availableseats;

	public Bus() {}

	public Bus(String id, String name, String runningtime, int price, int availableseats) {
		this.id = id;
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

	public int getAvailableseats() {
		return availableseats;
	}

	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
