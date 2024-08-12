package com.flipkart.client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Student;
import com.flipkart.business.StudentOperations;
import com.flipkart.business.StudentInterface;

class StudentMenu {
	StudentInterface studentService = new StudentOperations();

	/**
	 * Displays the student menu and processes user selections for various student operations.
	 * @param student The student user performing the operations.
	 * @param username The username of the student.
	 */
	void studentMenu(Student student, String username) {
		Scanner s = new Scanner(System.in);
		int in = 0;
		System.out.println("Welcome to CRS Application " + username);
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("LOGIN TIME: " + localDateTime);

		while (in != 6) {
			System.out.println("Student Menu:");
			System.out.println("1. Register Courses\n2. View Courses\n3. View Report Card\n4. Billing info\n5. Make Payment\n6. Exit");
			
			in = s.nextInt();
			switch (in) {
				case 1:
					registerCourses(student);
					break;
				case 2:
					viewCourses(student);
					break;
				case 3:
					viewReport(student);
					break;
				case 4:
					billingInfo(student);
					break;
				case 5:
					makePayment(student);
					break;
				case 6:
					continue;
				default:
					System.out.println("Invalid choice");
			}
		}
	}
	
	/**
	 * Displays billing information for the student.
	 * @param student The student user requesting billing info.
	 */
	public void billingInfo(Student student) {
		System.out.println(studentService.getBillingInfo(student));
	}

	/**
	 * Displays the student's report card.
	 * @param student The student user requesting their report card.
	 */
	private void viewReport(Student student) {
		System.out.println(studentService.getReport(student));
	}

	/**
	 * Displays the courses the student is currently enrolled in.
	 * @param student The student user requesting to view their enrolled courses.
	 */
	private void viewCourses(Student student) {
		String courses = studentService.viewCoursesEnrolled(student);
		System.out.println(courses);
	}

	/**
	 * Registers courses for the student. The student can select a set of courses from the catalog.
	 * @param student The student user registering for courses.
	 */
	private void registerCourses(Student student) {
		if (!student.isApproved()) return;

		List<String> courses = new ArrayList<>();
		Scanner s = new Scanner(System.in);

		for (int count = 0; count < 6; count++) {
			System.out.println(studentService.viewCourses());
			if (count < 4) {
				System.out.println("Select primary course " + (count + 1) + " from catalog:");
			} else {
				System.out.println("Select secondary course " + (count - 3) + " from catalog:");
			}
			String courseID = s.next();
			courses.add(courseID);
		}

		String registered = studentService.register(student, courses);
		String[] splitArray = registered.split("\n");
		String floatval = splitArray[splitArray.length - 1].split(" ")[1];
		float price = Float.parseFloat(floatval);
		
		System.out.println("Following courses were registered successfully:");
		System.out.println(registered);
	}

	/**
	 * Handles the payment process for the student. The student provides a transaction ID to complete the payment.
	 * @param student The student user making the payment.
	 */
	private void makePayment(Student student) {
		float price = studentService.getCoursePricing(student);
		System.out.println("Enter transaction ID:");
		Scanner s = new Scanner(System.in);
		String transactionID = s.next();
		String paymentStatus = studentService.makePayment(student, price, transactionID);
		System.out.println(paymentStatus);
	}
}
