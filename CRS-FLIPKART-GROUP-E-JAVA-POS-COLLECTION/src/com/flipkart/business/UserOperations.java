package com.flipkart.business;

import java.util.HashMap;
import java.util.Map;

import com.flipkart.bean.*;

public class UserOperations implements UserInterface{
	Map<String,User> users =new HashMap<>();
	

	
	public UserOperations(){
		users.put("admin1",new Admin("admin1", "dubey", "123456789", "abc@abc.com", "abc"));
		users.put("prof1",new Prof("prof1", "manis", "123456799", "abc2@abc.com", "arts", "postgrad", "xdd"));
		users.put("admin2",new Admin("admin2", "duba", "123456709", "def@abc.com", "def"));
		users.put("student1",new Student("student1", "chotu", "123356789", "stu@abc.com", "cs", 0, null, "chotu"));
	}
	
	public User retrieve(String username, String password) {
		User user=users.get(username);
		if(password.equals(user.getPassword())) {
			return user;
		}return null;
	}
	
	public void makeNew(String username, User user){
		users.put(username, user);
	}
	
	public boolean changePassword(String username, String password, String newPassword){
		User user=users.get(username);
		if(user.getPassword().equals(password)) {
			user.setPassword(newPassword);
			return true;
		}return false;
	}
	
	public boolean registerStudent(String username, Student student) {
		if(users.get(username).equals(null))return false;
		users.put(username, student);
		return true;
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
