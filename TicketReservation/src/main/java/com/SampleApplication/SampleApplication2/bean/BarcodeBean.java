package com.SampleApplication.SampleApplication2.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;

@ManagedBean(name="barcodeBean",eager=true)
@ViewScoped
public class BarcodeBean {

	private String genId;
	private String id;

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 */
	public String getGenId() {
		return genId;
	}
	/**
	 * 
	 * @param string
	 */
	public void setGenId(String string) {
		this.genId = string;
	}
}
