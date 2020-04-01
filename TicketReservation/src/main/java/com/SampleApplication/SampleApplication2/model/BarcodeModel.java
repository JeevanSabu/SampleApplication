package com.SampleApplication.SampleApplication2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "barcoeModel" , eager = true)
@SessionScoped
public class BarcodeModel {
	private String result = "authentication";

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
