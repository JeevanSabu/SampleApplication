package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "busBean" , eager = true)
@ViewScoped
public class BusBean {
	private String selectedSeat;
	
	/**
	 * 
	 * @return
	 */
	public String getSelectedSeat() {
		return selectedSeat;
	}
	/**
	 * 
	 * @param selectedSeat
	 */
	public void setSelectedSeat(String selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
}
