package com.flipkart.dao;

import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;

public interface AdminDaoInterface {
	public String addProf(Prof prof, String username);
	
	public boolean removeProf(String profID);
	
	public boolean updateCourse(String courseCode, Course updatedCourse);
	
	public boolean addCourse(Course course);
	
	public boolean removeCourse(String courseID);
	
	public boolean registerStudent(String studentID);
	
	public Set<Course> viewCourses();
	
	public Set<Prof> viewProfessors();
	
	public Set<Student> viewUnapprovedStudents();
}
