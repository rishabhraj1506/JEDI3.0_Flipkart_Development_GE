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
		int input=0;
		String username = null,password = null;
		userInstance=new UserOperations();
		catalog=new Catalog();
		while(input!=4) {
			Scanner s=new Scanner(System.in);
			System.out.println("Welcome to the CRS Application :-->");
			System.out.println("Press 1:--> Login");
			System.out.println("Press 2:--> Registration");
			System.out.println("Press 3:--> Update Password");
			System.out.println("Press 4:--> Exit");
			input=s.nextInt();
			switch(input) {
			case 1:
				System.out.println("Enter the Username :--");
				username=s.next();
				System.out.println("Enter the Password :--");
				password=s.next();
				User user=userInstance.retrieve(username, password);
				if(user.getRole().equals("Student")) {
					StudentMenu studentOps=new StudentMenu();
					studentOps.studentMenu((Student)user);
				}else if(user.getRole().equals("Professor")) {
					ProfMenu profops= new ProfMenu();
					profops.professorMenu((Prof)user);
				}else if(user.getRole().equals("Admin")) {
					AdminMenu adminops=new AdminMenu();
					adminops.adminMenu((Admin)user);
				}
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
				System.out.println("Invalid commaand");
			}
		}
	}
	

	public static void studentRegistraion() {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter Username :");
		String username=s.next();
		System.out.println("Enter Name :");
		String name=s.nextLine();
		System.out.println("Enter Details in following format: \n<ID> <contact> <email> <branch> <roll number> <password");
		String ID=s.next(), contact=s.next(), email=s.next(), branch= s.next();
		int rollNum=s.nextInt();
		String password=s.next();
		Student student=new Student(ID,name,contact,email,branch,rollNum,null,password);
		userInstance.registerStudent(username,student);		
		
	}
	
	public static void updatePassword() {
		Scanner s=new Scanner(System.in);
		String username = null,password = null;
		System.out.println("Enter the Username :--");
		username=s.next();
		System.out.println("Enter old Password :--");
		password=s.next();
		System.out.println("Enter new Password :--");
		String newPassword=s.next();
		userInstance.changePassword(username,password,newPassword);
		
		
		
	}
	
	public static UserOperations getUsers() {
		return userInstance;
	}
	
	public static Catalog getCatalog() {
		return catalog;
	}
}
