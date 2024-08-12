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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return false;
	}
	
	public String registerStudent(String username, String name, String contact, 
			String email, String password, String branch) {
		try {
			return udi.registerStudent(username, name, contact, email, password, branch);
		} catch (UsernameAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User findByID(String id) {
		for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user.getID().equals(id)) {
                return user; // Return the user if the ID matches
            }
        }
        return null;
	}
	
	public void printUsers() {
		for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
			if(user.getRole()!="Student")continue;
			Student stu=(Student)user;
            System.out.println(stu.getID()+"-"+stu.getName());
        }
	}
}