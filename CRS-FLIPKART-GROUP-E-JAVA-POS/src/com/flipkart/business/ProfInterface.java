package com.flipkart.business;

import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;

public interface ProfInterface {
	public String offerCourse(String courseID, Prof prof);
	
	public String getStudents(String courseID, Prof prof);
	
	public String giveGrade(String courseID, String studentID, String grade, Prof prof);
	
	public String viewCourses();
	
	public String viewCourseOffering(Prof prof);
}
