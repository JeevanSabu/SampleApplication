package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.SampleApplication.SampleApplication2.jerseyclient.Bus;
@ManagedBean(name="busView", eager=true)
@SessionScoped
public class BusView {
	private List<Bus> buses;
	private String selectedBus;

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public String getSelectedBus() {
		return selectedBus;
	}

	public void setSelectedBus(String selectedBus) {
		this.selectedBus = selectedBus;
	}
}
