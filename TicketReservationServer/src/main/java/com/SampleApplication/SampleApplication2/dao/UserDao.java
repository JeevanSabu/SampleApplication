package com.SampleApplication.SampleApplication2.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.pojo.UserPojo;

public class UserDao {

	private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
	private static List<UserPojo> users;
	static {
		users.add(new UserPojo("Jerin","Jerin@123"));
		users.add(new UserPojo("Jeeson","Jeeson@123"));
	}
	public UserPojo getUser(String username, String password) {
		for(UserPojo userPojo : users) {
			if(userPojo.getUsername().equals(username)&&userPojo.getPassword().equals(password)){
				return userPojo;
			}
		}
		return null;
	}

}
