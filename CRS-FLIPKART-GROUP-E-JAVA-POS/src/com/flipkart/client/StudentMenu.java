package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.Student;
import com.flipkart.business.StudentOperations;
import com.flipkart.business.StudentInterface;

class StudentMenu {
	StudentInterface studentService=new StudentOperations();

	void studentMenu(Student student) {
		// TODO Auto-generated method stub
		
		Scanner s=new Scanner(System.in);
		int in =0;
		while(in!=5) {
			StudentOperations studentOperations = new StudentOperations();
			System.out.println("Student Menu:");
			System.out.println("1. Register Courses\n2. View Courses\n3. View Report Card\n4. Billing info\n5. Exit");
			
			in=s.nextInt();
			switch(in) {
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
	                System.out.println("Enter payment amount:");
	                float amount = s.nextFloat();
	                System.out.println("Enter payment type (e.g., CreditCard, NetBanking):");
	                String paymentType = s.next();
	                String paymentStatus = studentOperations.makePayment(student, amount, paymentType);
	                System.out.println(paymentStatus);
	                break;
	            case 6:
	                continue;
				default:
					System.out.println("Invalid");
			}
		}
	}
	
	public void billingInfo(Student student) {
		// TODO Auto-generated method stub
		System.out.println(studentService.getBillingInfo(student));
	}

	private void viewReport(Student student) {
		// TODO Auto-generated method stub
		System.out.println(studentService.getReport(student));
	}

	private void viewCourses(Student student) {
		// TODO Auto-generated method stub
		String courses=studentService.viewCoursesEnrolled(student);
		System.out.println(courses);
	};

	private void registerCourses(Student student) {
		// TODO Auto-generated method stub
		if(!student.isApproved())return ;
		List<String>courses=new ArrayList<String>();
		for(int count=0; count<6; count++) {
			System.out.println(studentService.viewCourses());
			if(count<4)System.out.println("Select primary course "+(count+1)+" from catalog:");
			else System.out.println("Select secondary course "+(count-3)+" from catalog:");
			Scanner s= new Scanner(System.in);
			String courseID=s.next();
			courses.add(courseID);
		}
		String registered=studentService.register(student, courses);
		/*String[] splitArray = registered.split("\n");
		String floatval=splitArray[splitArray.length-1].split(" ")[1];
		float price=Float.parseFloat(floatval);
		*/
		System.out.println("Following courses were registered successfully");
		System.out.println(registered);
		
		//if(studentService.register(student, courses))System.out.println("registration success");
		//else System.out.println("registration failed");
	}

}
