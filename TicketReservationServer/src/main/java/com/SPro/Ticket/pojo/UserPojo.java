package com.SPro.Ticket.pojo;

public class UserPojo {
	private String username;
	private String password;
	private String lastlogin;
	
	public UserPojo(){
		
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @param lastlogin
	 */
	public UserPojo(String username, String password, String lastlogin) {
		this.username = username;
		this.password = password;
		this.lastlogin = lastlogin;
	}
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
}
