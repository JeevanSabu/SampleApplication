package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="barcodeBean",eager=true)
@SessionScoped
public class BarcodeBean {

	private int genId;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getGenId() {
		return genId;
	}

	public void setGenId(int generatedId) {
		this.genId = generatedId;
	}
}
