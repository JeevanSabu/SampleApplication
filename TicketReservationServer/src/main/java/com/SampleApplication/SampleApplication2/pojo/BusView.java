package com.SampleApplication.SampleApplication2.pojo;

import java.util.List;
public class BusView {
	private List<Bus> buses;
	private Bus selectedBus;
	public Bus getSelectedBus() {
		return selectedBus;
	}

	public void setSelectedBus(Bus selectedBus) {
		this.selectedBus = selectedBus;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

}
