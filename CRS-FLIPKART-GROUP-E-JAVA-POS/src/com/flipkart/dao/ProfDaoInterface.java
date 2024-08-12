package com.flipkart.dao;

import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotAvailableException;
import com.flipkart.exception.CourseNotOfferedException;
import com.flipkart.exception.CourseNotOptedException;
import com.flipkart.exception.GradeAlreadyAddedException;

public interface ProfDaoInterface {
	public boolean offerCourse(String courseID, Prof prof) throws CourseNotAvailableException;
	
	public Set<Course> viewCourses();
	
	public Set<Student> getStudents(String courseID, Prof prof);
	
	public boolean giveGrade(String courseID, String studentID, String grade, Prof prof) throws CourseNotOptedException,GradeAlreadyAddedException,CourseNotOfferedException;
	
	public Set<Course> viewCourseOffering(Prof prof);
}
