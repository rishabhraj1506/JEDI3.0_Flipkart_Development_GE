package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfOperations;
import com.flipkart.business.ProfInterface;

public class ProfMenu {
	ProfInterface profService=new ProfOperations();
	
	public void professorMenu(Prof prof) {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		int in =0;
		while(in!=4) {
			System.out.println("Professor Menu:");
			System.out.println("1. Offer Course\n2. View Registered Students\n3. Submit Grades\n4. Exit");
		
			in=s.nextInt();
			switch(in) {
				case 1: 
					offerCourse(prof);
					break;
				case 2:
					viewRegisteredStudent(prof);
					break;
				case 3:
					submitGrade(prof);
					break;
				case 4:
					continue;
				default:
					System.out.println("Invalid");
			}
		}
	}

	private void offerCourse(Prof prof) {
		// TODO Auto-generated method stub
		System.out.println("Enter Course ID from the following:");
		System.out.println(profService.viewCourses());
		Scanner s= new Scanner(System.in);
		String courseID=s.next();
		System.out.println(profService.offerCourse(courseID,prof));
	}

	private void viewRegisteredStudent(Prof prof) {
		// TODO Auto-generated method stub
		System.out.println("Course ID:");
		Scanner s= new Scanner(System.in);
		String courseID=s.next();
		String studentList=profService.getStudents(courseID,prof);
		System.out.println(studentList);
	}

	private void submitGrade(Prof prof) {
		// TODO Auto-generated method stub
		System.out.println("Course ID:");
		Scanner s= new Scanner(System.in);
		String courseID=s.next();
		System.out.println("Student ID:");
		String studentID=s.next();
		System.out.println("Grade:");
		String grade=s.next();
		System.out.println(profService.giveGrade(courseID, studentID, grade, prof));
		
	}
	

}
