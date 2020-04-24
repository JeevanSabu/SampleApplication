package com.SPro.Ticket.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean {
	private String username;
	private String password;
	private String lastLogin;
	private String genId;
	
	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return
	 */
	public String getLastLogin() {
		return lastLogin;
	}
	/**
	 * 
	 * @param lastLogin
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
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
	 * @param genId
	 */
	public void setGenId(String genId) {
		this.genId = genId;
	}
}
