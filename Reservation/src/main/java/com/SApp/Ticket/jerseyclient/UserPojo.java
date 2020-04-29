package com.SApp.Ticket.jerseyclient;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userPojo" , eager = true)
@SessionScoped
public class UserPojo {
	private String username;
	private String password;
	private String lastlogin;
	private String accessToken;
	
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
	public String getLastlogin() {
		return lastlogin;
	}
	/**
	 * 
	 * @param lastlogin
	 */
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	/**
	 * 
	 * @return
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
