package com.flipkart.business;

import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface AdminInterface {
	public String addProf(Prof prof, String username);
	
	public String removeProf(String profID);
	
	public String updateCourse(String courseCode, Course updatedCourse);
	
	public String addCourse(Course course);
	
	public String removeCourse(String courseID);
	
	public String registerStudent(String studentID);
	
	public String viewCourses();
	
	public String viewProfessors();
	
	public String viewUnapprovedStudents();
}
