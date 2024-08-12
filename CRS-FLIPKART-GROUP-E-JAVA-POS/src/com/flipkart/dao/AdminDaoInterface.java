package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;

public interface AdminDaoInterface {
	public String addProf(Prof prof, String username);
	
	public boolean removeProf(String profID);
	
	public boolean updateCourse(String courseCode, Course updatedCourse);
	
	public boolean addCourse(Course course);
	
	public boolean removeCourse(String courseID);
	
	public boolean registerStudent(String studentID);
}
