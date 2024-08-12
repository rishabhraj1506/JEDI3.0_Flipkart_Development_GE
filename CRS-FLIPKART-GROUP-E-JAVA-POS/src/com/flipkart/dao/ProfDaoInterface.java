package com.flipkart.dao;

import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;

public interface ProfDaoInterface {
	public boolean offerCourse(String courseID, Prof prof);
	
	public Set<Course> viewCourses();
	
	public Set<Student> getStudents(String courseID, Prof prof);
	
	public boolean giveGrade(String courseID, String studentID, String grade, Prof prof);
	
	public Set<Course> viewCourseOffering(Prof prof);
}
