package com.flipkart.business;

import java.util.HashMap;
import java.util.Map;

import com.flipkart.bean.*;

public class AdminOperations implements AdminInterface{
    /**
     * Method to add a professor
     * @param professor: the professor to add
     */
    public void addProf(Prof prof, UserOperations userInstance, String username) {
    	userInstance.makeNew(username,(User)prof);
    }

    /**
     * Method to remove a professor
     * @param professorID: the ID of the professor to remove
     * @return true if professor was removed successfully, false otherwise
     */
    public boolean removeProf(User prof) {
    	prof.setRole("user");
        return false; // Professor ID not found
    }

    /**
     * Method to update a course
     * @param courseCode: the code of the course to update
     * @param updatedCourse: the updated course details
     * @return true if course was updated successfully, false otherwise
     */
    public boolean updateCourse(String courseCode, Course updatedCourse, Catalog catalog) {
        catalog.removeCourse(courseCode);
        catalog.addCourse(updatedCourse);
    	return false;
    }

    /**
     * Method to add a course
     * @param course: the course to add
     */
    public void addCourse(Course course,Catalog catalog) {
    	//
    	catalog.addCourse(course);
    }

    /**
     * Method to remove a course
     * @param courseCode: the code of the course to remove
     * @return true if course was removed successfully, false otherwise
     */
    public boolean removeCourse(String courseCode, Catalog catalog) {
        return catalog.removeCourse(courseCode);
    }

    /**
     * Method to register a student
     * @param student: the student to register
     */
    public void registerStudent(Student student) {
    	student.setStatus(true);
    }
}
