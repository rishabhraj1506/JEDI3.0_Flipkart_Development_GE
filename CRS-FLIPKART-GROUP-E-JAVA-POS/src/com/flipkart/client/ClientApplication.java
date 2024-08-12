package com.flipkart.client;

import java.util.List;
import java.util.ArrayList;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.*;
import java.util.Scanner;
import java.util.Set;

public class ClientApplication {
	static UserOperations userInstance;
	static Catalog catalog;
	
	public static void main(String[] args) {
		int input = 0;
		String username = null, password = null;
		userInstance = new UserOperations();
		catalog = new Catalog();
		Scanner s = new Scanner(System.in);
		
		// Main menu loop
		while (input != 4) {
			System.out.println("Welcome to the CRS Application :-->");
			System.out.println("Press 1:--> Login");
			System.out.println("Press 2:--> Registration");
			System.out.println("Press 3:--> Update Password");
			System.out.println("Press 4:--> Exit");
			input = s.nextInt();
			
			switch (input) {
				case 1:
					System.out.println("Enter the Username :--");
					username = s.next();
					System.out.println("Enter the Password :--");
					password = s.next();
					login(username, password);
					break;
				case 2:
					studentRegistraion();
					break;
				case 3:
					updatePassword();
					break;
				case 4:
					continue;
				default:
					System.out.println("Invalid command");
			}
		}
	}
	
	/**
	 * Handles user login based on username and password.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 */
	public static void login(String username, String password) {
		User user = userInstance.retrieve(username, password);
		
		if (user == null) {
			System.out.println("Wrong username or password, exiting main menu...");
			return;
		}
		
		if (user.getRole().equals("Student")) {
			if (!((Student)user).isApproved()) return;
			StudentMenu studentOps = new StudentMenu();
			studentOps.studentMenu((Student)user, username);
		} else if (user.getRole().equals("Professor")) {
			ProfMenu profops = new ProfMenu();
			profops.professorMenu((Prof)user, username);
		} else if (user.getRole().equals("Admin")) {
			AdminMenu adminops = new AdminMenu();
			adminops.adminMenu((Admin)user, username);
		}
	}
	
	/**
	 * Handles student registration by taking user input and registering a new student.
	 */
	public static void studentRegistraion() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Username :");
		String username = s.next();
		s.nextLine();
		System.out.println("Enter Name :");
		String name = s.nextLine();
		System.out.println("Enter Details in the following format: \n<contact> <email> <branch> <password>");
		
		String details = s.nextLine();
        
        // Split the details input into separate fields
        String[] detailsArray = details.split(" ");
        
		String contact = detailsArray[0], email = detailsArray[1], 
				branch = detailsArray[2], password = detailsArray[3];
		
		System.out.println(userInstance.registerStudent(username, name, contact, email, password, branch));		
	}
	
	/**
	 * Handles password update for a user.
	 * @param username The username of the user whose password needs to be updated.
	 * @param password The old password of the user.
	 * @param newPassword The new password to be set.
	 */
	public static void updatePassword() {
		Scanner s = new Scanner(System.in);
		String username, password, newPassword;
		System.out.println("Enter the Username :--");
		username = s.next();
		System.out.println("Enter old Password :--");
		password = s.next();
		System.out.println("Enter new Password :--");
		newPassword = s.next();
		
		if (userInstance.changePassword(username, password, newPassword)) {
			System.out.println("Password changed successfully");
		}
	}
}
