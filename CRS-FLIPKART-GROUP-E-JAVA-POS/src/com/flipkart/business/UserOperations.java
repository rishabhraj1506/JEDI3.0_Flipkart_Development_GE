package com.flipkart.business;

import java.util.HashMap;
import java.util.Map;

import com.flipkart.bean.*;
import com.flipkart.dao.UserDaoServices;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.UsernameAlreadyInUseException;
import com.flipkart.dao.UserDaoInterface;

public class UserOperations implements UserInterface{
	//Map<String,User> users =new HashMap<>();
	UserDaoInterface udi=new UserDaoServices()
;	
	public User retrieve(String username, String password) {
		User user;
		try {
			user = udi.getUser(username);
			if(password.equals(user.getPassword())) 
				return user;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}return null;
	}
	
	public void makeNew(String username, User user){
		users.put(username, user);
	}
	
	public boolean changePassword(String username, String password, String newPassword){
		try {
			return udi.updatePassword(username, password, newPassword);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return false;
	}
	
	public String registerStudent(String username, String name, String contact, 
			String email, String password, String branch) {
		try {
			return udi.registerStudent(username, name, contact, email, password, branch);
		} catch (UsernameAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return null;
	}
	
	public User findByID(String id) {
		return users.values().stream()
	            .filter(user -> user.getID().equals(id))
	            .findFirst()
	            .orElse(null);
	}
	
	public void printUsers() {
		users.entrySet().stream()
        .map(Map.Entry::getValue)
        .filter(user -> "Student".equals(user.getRole()))
        .map(user -> (Student) user)
        .forEach(stu -> System.out.println(stu.getID() + "-" + stu.getName()));
	}
}