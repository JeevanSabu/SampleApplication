package com.SampleApplication.SampleApplication2.dao;

import java.util.List;

import com.SampleApplication.SampleApplication2.pojo.User;

public class UserDao {

	private static List<User> users;
	static {
		users.add(new User("Jerin","Jerin@123"));
		users.add(new User("Jeeson","Jeeson@123"));
	}
	public User getUser() {
		
		return null;
	}

}
