package com.flipkart.business;

import com.flipkart.bean.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class ProfOperations implements ProfInterface{
    /**
     * Method to offer a course
     * @param course: the course to be offered
     */
    public void offerCourse(Course course, Prof prof) {
        course.setCourseProf(prof);
        prof.addCourse(course);
        //
    }

    /**
     * Method to get the registered students for a given course
     * @param course: the course for which to get the registered students
     * @return set of registered students
     */
    public Set<Student> getStudents(String courseID, Prof prof) {
    	return prof.getRegisteredStudents(courseID);
    }

    /**
     * Method to give a grade to a student in a course
     * @param course: the course in which the grade is to be given
     * @param student: the student to whom the grade is to be given
     * @param grade: the grade to be given
     * @return true if grade was successfully assigned, false otherwise
     */
    public boolean giveGrade(String courseID, String studentID, String grade, Prof prof) {
         // Student is not registered for the course
    	Set<Student> studentList=prof.getRegisteredStudents(courseID);
    	
    	for(Student stu:studentList) {
    		if(stu.getID().equals(studentID)) {
    			stu.updateGrade(courseID, grade);
    			return true;
    		}
    	}
    	return false;
    }
}
