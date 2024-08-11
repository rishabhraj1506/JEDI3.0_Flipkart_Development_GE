package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.AdminOperations;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.UserOperations;

public class AdminMenu {
	AdminInterface adminService=new AdminOperations();
	public void adminMenu(Admin admin) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int in =0;
		while(in!=7) {
			System.out.println("Admin Menu:");
			System.out.println("1. Add Professor\n2. Remove Professor\n3. Modify Course\n4. Add Course\n5. Remove Course\n6. Approve Student Registration\n7. Exit");
			
			in=s.nextInt();
			switch(in) {
				case 1: 
					addProf(admin);
					break;
				case 2:
					removeProf(admin);
					break;
				case 3:
					updateCourse(admin);
					break;
				case 4:
					addCourse(admin);
					break;
				case 5:
					removeCourse(admin);
					break;
				case 6:
					approveRegistration(admin);
					break;
				case 7:
					continue;
				default:
					System.out.println("Invalid");
			}
		}
	}

	private void approveRegistration(Admin admin) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		UserOperations userInstance=ClientApplication.getUsers();
		userInstance.printUsers();
		String studentID=s.next();
		System.out.println("Student ID for approval:");
		Student student=(Student) userInstance.findByID(studentID);
		adminService.registerStudent(student);
	}

	private void removeCourse(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("Course Code to be removed:");
		Scanner s=new Scanner(System.in);
		String courseID=s.next();
		Catalog catalog=ClientApplication.getCatalog();
		adminService.removeCourse(courseID, catalog);
	}

	private void addCourse(Admin admin) {
		// TODO Auto-generated method stub
		Catalog catalog=ClientApplication.getCatalog();
		System.out.println("Enter Course details in following format:\n<courseID> <courseName> <seats>");
		Scanner s=new Scanner(System.in);
		String courseID=s.next(), courseName=s.next();
		int seats=s.nextInt();
		Course course=new Course(courseID,courseName,null,seats);
		adminService.addCourse(course, catalog);
	}

	private void updateCourse(Admin admin) {
		// TODO Auto-generated method stub
		Catalog catalog=ClientApplication.getCatalog();
		Scanner s=new Scanner(System.in);
		System.out.println("Enter old courseID");
		String oldCourseID=s.next();
		System.out.println("Enter Course details in following format:\n<courseID> <courseName> <seats> <profID>");
		String courseID=s.next(), courseName=s.next();
		int seats=s.nextInt();
		String profID=s.next();
		UserOperations userInstance=ClientApplication.getUsers();
		Prof prof = (Prof) userInstance.findByID(profID);
		Course course=new Course(courseID,courseName,prof,seats);
		adminService.updateCourse(oldCourseID, course, catalog);
	}

	private void removeProf(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("User ID for professor removal:");
		Scanner s=new Scanner(System.in);
		String profID=s.next();
		UserOperations userInstance=ClientApplication.getUsers();
		User prof = userInstance.findByID(profID);
		adminService.removeProf(prof);
	}

	private void addProf(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("Enter Prof details in following format:\n<username> <profID> <profName> <contact> <email> <dept> <qualification> <password>");
		Scanner s=new Scanner(System.in);
		String username=s.next(),profID=s.next(),profName=s.next(),contact=s.next(),email=s.next(),dept=s.next(),qualification=s.next(),password=s.next();
		UserOperations userInstance=ClientApplication.getUsers();
		Prof prof=new Prof(profID,profName,contact,email,dept,qualification,password);
		adminService.addProf(prof,userInstance,username);
	}

}
