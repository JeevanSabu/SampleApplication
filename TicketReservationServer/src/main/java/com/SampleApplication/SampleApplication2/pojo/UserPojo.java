package com.SampleApplication.SampleApplication2.pojo;

public class UserPojo {
	private String username;
	private String password;
	private String lastlogin;
	
	public UserPojo(){
		
	}
	
	public UserPojo(String username, String password, String lastlogin) {
		this.username = username;
		this.password = password;
		this.lastlogin = lastlogin;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
}
