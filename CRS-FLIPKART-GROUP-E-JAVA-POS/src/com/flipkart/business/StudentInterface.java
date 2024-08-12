package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

public interface StudentInterface {

	public String register(Student student, List<String> courses);
	
	public int getValidCount(List<String> courses);
	
	public String viewCoursesEnrolled(Student student);
	
	public String getReport(Student student);
	
	public String getBillingInfo(Student student);
	
	public String viewCourses();
}
