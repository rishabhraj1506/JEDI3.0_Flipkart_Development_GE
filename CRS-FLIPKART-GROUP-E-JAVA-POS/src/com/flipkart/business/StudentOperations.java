package com.flipkart.business;

import com.flipkart.bean.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentOperations implements StudentInterface {
    /**
     * Method to register the student in a course
     * @param course: the course to register
     * @return true if registration was successful, false otherwise
     */
	public boolean register(Student student, List<Course> courses) {
		int validCount=getValidCount(courses);
		if(validCount!=4||courses.size()!=6)return false;
		int count=0;
		for(Course course:courses) {
			if(count==4)break;
			if(course.isCourseFull())continue;
			student.addCourse(course);
			count++;
		}
		return true;
	}
    
    /**
     * Method to view courses registered by the student
     * @return list of registered courses
     */
	public int getValidCount(List<Course> courses) {
		int validCount=0;
		for(Course course:courses) {
			if(validCount==4)break;
			if(course.isCourseFull())continue;
			else validCount++;
		}return validCount;
	}
	
    public List<Course> viewCourses(Student student) {
        return student.courseList();
    }
    
    /**
     * Method to get a report of registered courses
     * @return a string report of registered courses
     */
    public String getReport(Student student) {
        return student.getReport();
    }
    
    /**
     * Method to get billing information
     * @return billing information
     */
    public String getBillingInfo(Student student) {
        return student.getBilling().infoAboutPay();
    }
}
