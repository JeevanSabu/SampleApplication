package com.SampleApplication.SampleApplication2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="homeModel" , eager=true)
@SessionScoped
public class HomeModel {
	private String result;

	public String getResult() {
		result = "booking";
		return result;
	}
}
