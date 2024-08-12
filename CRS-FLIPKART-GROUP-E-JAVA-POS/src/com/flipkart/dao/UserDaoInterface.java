package com.flipkart.dao;

import com.flipkart.bean.User;

public interface UserDaoInterface {
	User getUser(String username);

	String registerStudent(String username, String name, String contact, String email, String password, String branch);
	
	boolean updatePassword(String username, String oldPassword, String newPassword);
}
