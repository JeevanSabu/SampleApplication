package com.SampleApplication.SampleApplication2.tools;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name="busView", eager=true)
@SessionScoped
public class BusView {
	private static List<Bus> buses;
	private Bus selectedBus;
	static{
		buses.add(new Bus("Tippu Sultan","15.30 pm",1200,32));
		buses.add(new Bus("Kallada","17.30 pm",2500,25));
	}

	public Bus getSelectedBus() {
		return selectedBus;
	}

	public void setSelectedBus(Bus selectedBus) {
		this.selectedBus = selectedBus;
	}

	public static List<Bus> getBuses() {
		return buses;
	}

	public static void setBuses(List<Bus> buses) {
		BusView.buses = buses;
	}
}
