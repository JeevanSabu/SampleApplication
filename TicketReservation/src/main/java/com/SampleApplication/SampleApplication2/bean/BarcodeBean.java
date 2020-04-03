package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="barcodeBean",eager=true)
@SessionScoped
public class BarcodeBean {

	private String genId;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenId() {
		return genId;
	}

	public void setGenId(String string) {
		this.genId = string;
	}
}
