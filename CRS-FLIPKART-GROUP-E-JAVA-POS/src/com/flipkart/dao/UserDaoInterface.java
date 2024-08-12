package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.UsernameAlreadyInUseException;

public interface UserDaoInterface {
	User getUser(String username) throws UserNotFoundException;

	String registerStudent(String username, String name, String contact, String email, String password, String branch) throws UsernameAlreadyInUseException;
	
	boolean updatePassword(String username, String oldPassword, String newPassword) throws UserNotFoundException;
}
