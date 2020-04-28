package com.SPro.Ticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.SPro.Ticket.jerseyclient.Bus;

@ManagedBean(name="busView", eager=true)
@SessionScoped
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
