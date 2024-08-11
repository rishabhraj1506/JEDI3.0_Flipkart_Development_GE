package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
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
					continue;
				default:
					System.out.println("Invalid");
			}
		}
	}
	
	public String billingInfo(Student student) {
		// TODO Auto-generated method stub
		return studentService.getBillingInfo(student);
	}

	private String viewReport(Student student) {
		// TODO Auto-generated method stub
		return studentService.getReport(student);
	}

	private void viewCourses(Student student) {
		// TODO Auto-generated method stub
		List<Course> list=studentService.viewCourses(student);
		for(Course course:list) {
			System.out.println(course.getCourseName()+":"+course.getCourseID());
		}
	};

	private void registerCourses(Student student) {
		// TODO Auto-generated method stub
		if(!student.isStatus())return ;
		List<Course>courses=fetchCourseList();
		if(studentService.register(student, courses))System.out.println("registration success");
		else System.out.println("registration failed");
	}
	
	private List<Course> fetchCourseList(){
		List<Course> courses=new ArrayList<Course>();
		Catalog catalog=ClientApplication.getCatalog();
		for(int count=0; count<6; count++) {
			if(count<4)System.out.println("Select primary course "+(count+1)+" from catalog:");
			else System.out.println("Select secondary course "+(count-3)+" from catalog:");
			for(Course course:catalog.getCourses()) {
				System.out.println(course.getCourseID()+" "+course.getCourseName()+" "+course.getCourseProf());
			}Scanner s= new Scanner(System.in);
			String cid=s.next();
			Course course=null;
			for(Course tempCourse:catalog.getCourses()) {
				if(cid.equals(tempCourse.getCourseID())) {
					course=tempCourse;
					break;
				}
			}
			courses.add(course);
		}
		return courses;
	}

}
