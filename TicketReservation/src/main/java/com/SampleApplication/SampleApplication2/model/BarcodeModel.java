package com.SampleApplication.SampleApplication2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "barcodeModel" , eager = true)
@SessionScoped
public class BarcodeModel {
	private String result = "authentication";

	
	public String getResult() {
		
		result="home";
		return result;
	}

}
