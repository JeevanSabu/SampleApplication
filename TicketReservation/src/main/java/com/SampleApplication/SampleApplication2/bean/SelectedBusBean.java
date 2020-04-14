package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.SampleApplication.SampleApplication2.jerseyclient.Bus;

@ManagedBean(name="selectedBusBean" , eager=true)
@SessionScoped
public class SelectedBusBean {
	private String id;
	private Bus selectedBus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bus getSelectedBus() {
		return selectedBus;
	}

	public void setSelectedBus(Bus selectedBus) {
		this.selectedBus = selectedBus;
	}
	
}
