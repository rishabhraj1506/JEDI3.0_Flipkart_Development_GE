package com.flipkart.business;

import java.util.HashMap;
import java.util.Map;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface UserInterface {
	Map<String,User> users =new HashMap<>();
	
	public User retrieve(String username, String password);
	
	public void makeNew(String username, User user);
	
	public boolean changePassword(String username, String password, String newPassword);
	
	public String registerStudent(String username, String name, String contact, String email, String password, String branch);
	
	public User findByID(String id);
	
	public void printUsers();
}
