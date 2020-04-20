package com.SampleApplication.SampleApplication2.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.SampleApplication.SampleApplication2.jerseyclient.Bus;
@ManagedBean(name="busView", eager=true)
@ViewScoped
public class BusView {
	private List<Bus> buses;
	private Bus selectedBus;

	/**
	 * 
	 * @return
	 */
	public List<Bus> getBuses() {
		return buses;
	}
	/**
	 * 
	 * @param buses
	 */
	public void setBuses(List<Bus> buses) {
		this.buses = buses;
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
}
