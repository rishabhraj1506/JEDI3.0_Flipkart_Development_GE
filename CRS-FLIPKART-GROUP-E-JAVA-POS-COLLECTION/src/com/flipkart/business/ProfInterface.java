package com.flipkart.business;

import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;

public interface ProfInterface {
	public void offerCourse(Course course, Prof prof);
	
	public Set<Student> getStudents(String courseID, Prof prof);
	
	public boolean giveGrade(String courseID, String studentID, String grade, Prof prof);
}
