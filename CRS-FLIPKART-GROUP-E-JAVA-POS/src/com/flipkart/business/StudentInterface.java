package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

public interface StudentInterface {

	public boolean register(Student student, List<Course> courses);
	
	public int getValidCount(List<Course> courses);
	
	public List<Course> viewCourses(Student student);
	
	public String getReport(Student student);
	
	public String getBillingInfo(Student student);
}
