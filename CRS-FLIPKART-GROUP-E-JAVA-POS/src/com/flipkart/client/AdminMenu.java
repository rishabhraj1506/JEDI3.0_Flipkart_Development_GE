package com.flipkart.client;

import java.time.LocalDateTime;
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
	AdminInterface adminService = new AdminOperations();

	/**
	 * Displays the admin menu and processes user selections for various admin operations.
	 * @param admin The admin user performing the operations.
	 * @param username The username of the admin.
	 */
	public void adminMenu(Admin admin, String username) {
		System.out.println("Welcome to CRS Application " + username);
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("LOGIN TIME: " + localDateTime);
		
		Scanner s = new Scanner(System.in);
		int in = 0;
		
		while (in != 7) {
			System.out.println("1. Add Professor\n2. Remove Professor\n3. Modify Course\n4. Add Course\n5. Remove Course\n6. Approve Student Registration\n7. Exit");
			in = s.nextInt();
			
			switch (in) {
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
					System.out.println("Invalid choice");
			}
		}
	}

	/**
	 * Approves a student's registration.
	 * @param admin The admin user performing the operation.
	 */
	private void approveRegistration(Admin admin) {
		Scanner s = new Scanner(System.in);
		System.out.println(adminService.viewUnapprovedStudents());
		System.out.println("Student ID for approval:");
		String studentID = s.next();
		System.out.println(adminService.registerStudent(studentID));
	}

	/**
	 * Removes a course from the catalog.
	 * @param admin The admin user performing the operation.
	 */
	private void removeCourse(Admin admin) {
		System.out.println("Existing Courses:");
		System.out.println(adminService.viewCourses());
		System.out.println("Course Code to be removed:");
		Scanner s = new Scanner(System.in);
		String courseID = s.next();
		System.out.println(adminService.removeCourse(courseID));
	}

	/**
	 * Adds a new course to the catalog.
	 * @param admin The admin user performing the operation.
	 */
	private void addCourse(Admin admin) {
		System.out.println("Existing Courses:");
		System.out.println(adminService.viewCourses());
		System.out.println("Enter Course details in the following format:\n<courseID> <courseName> <seats> <price>");
		Scanner s = new Scanner(System.in);
		String courseID = s.next();
		String courseName = s.next();
		int seats = s.nextInt();
		float price = s.nextFloat();
		Course course = new Course(courseID, courseName, null, seats, price);
		System.out.println(adminService.addCourse(course));
	}

	/**
	 * Updates the details of an existing course.
	 * @param admin The admin user performing the operation.
	 */
	private void updateCourse(Admin admin) {
		System.out.println("Existing Courses:");
		System.out.println(adminService.viewCourses());
		Scanner s = new Scanner(System.in);
		System.out.println("Enter old courseID");
		String oldCourseID = s.next();
		System.out.println("Enter Course details in the following format:\n<courseID> <courseName> <seats> <profID> <price>");
		String courseID = s.next();
		String courseName = s.next();
		int seats = s.nextInt();
		String profID = s.next();
		float price = s.nextFloat();
		Course course = new Course(courseID, courseName, profID, seats, price);
		adminService.updateCourse(oldCourseID, course);
	}

	/**
	 * Removes a professor from the system.
	 * @param admin The admin user performing the operation.
	 */
	private void removeProf(Admin admin) {
		System.out.println(adminService.viewProfessors());
		System.out.println("User ID for professor removal:");
		Scanner s = new Scanner(System.in);
		String profID = s.next();
		adminService.removeProf(profID);
	}

	/**
	 * Adds a new professor to the system.
	 * @param admin The admin user performing the operation.
	 */
	private void addProf(Admin admin) {
		System.out.println("Enter Prof details in the following format:\n<username> <profName> <contact> <email> <dept> <qualification> <password>");
		Scanner s = new Scanner(System.in);
		String username = s.next();
		String profName = s.next();
		String contact = s.next();
		String email = s.next();
		String dept = s.next();
		String qualification = s.next();
		String password = s.next();
		Prof prof = new Prof(null, profName, contact, email, dept, qualification, password);
		System.out.println(adminService.addProf(prof, username));
	}
}
