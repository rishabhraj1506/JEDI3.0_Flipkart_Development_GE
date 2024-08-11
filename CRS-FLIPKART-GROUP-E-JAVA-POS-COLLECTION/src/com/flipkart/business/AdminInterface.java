package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface AdminInterface {
	public void addProf(Prof prof, UserOperations userInstance, String username);
	
	public boolean removeProf(User prof);
	
	public boolean updateCourse(String courseCode, Course updatedCourse, Catalog catalog);
	
	public void addCourse(Course course,Catalog catalog);
	
	public boolean removeCourse(String courseCode, Catalog catalog);
	
	public void registerStudent(Student student);
}
