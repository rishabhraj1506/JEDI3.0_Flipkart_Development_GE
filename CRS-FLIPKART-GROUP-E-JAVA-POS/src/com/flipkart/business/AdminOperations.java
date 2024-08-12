package com.flipkart.business;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.flipkart.bean.*;
import com.flipkart.dao.AdminDaoServices;
import com.flipkart.dao.AdminDaoInterface;

public class AdminOperations implements AdminInterface{
    /**
     * Method to add a professor
     * @param professor: the professor to add
     */
	AdminDaoInterface adi=new AdminDaoServices();
	
    public String addProf(Prof prof, String username) {
		String userID=adi.addProf(prof, username);
    	if(!userID.isEmpty())return "Professor Added with id: "+userID;
    	return "Operation Failed...";
    	//userInstance.makeNew(username,(User)prof);
    }

    /**
     * Method to remove a professor
     * @param professorID: the ID of the professor to remove
     * @return true if professor was removed successfully, false otherwise
     */
    public String removeProf(String profID) {
    	//prof.setRole("user");
    	if(adi.removeProf(profID))return "Professor removed successfully";
    	return "Operation Failed..."; // Professor ID not found
    }

    /**
     * Method to update a course
     * @param courseCode: the code of the course to update
     * @param updatedCourse: the updated course details
     * @return true if course was updated successfully, false otherwise
     */
    public String updateCourse(String courseID, Course updatedCourse) {
        //catalog.removeCourse(courseCode);
        //catalog.addCourse(updatedCourse);
    	if(adi.updateCourse(courseID, updatedCourse))return "Course information updated successfully";
    	return "Operation Failed...";
    }

    /**
     * Method to add a course
     * @param course: the course to add
     */
    public String addCourse(Course course) {
    	//
    	//catalog.addCourse(course);
    	if(adi.addCourse(course))return "Course added Successfully";
    	return "Operation Failed...";
    }

    /**
     * Method to remove a course
     * @param courseCode: the code of the course to remove
     * @return true if course was removed successfully, false otherwise
     */
    public String removeCourse(String courseID) {
        //return catalog.removeCourse(courseCode);
    	if(adi.removeCourse(courseID))return "Course removed Successfully";
    	return "Operation Failed...";
    }

    /**
     * Method to register a student
     * @param student: the student to register
     */
    public String registerStudent(String studentID) {
    	if(adi.registerStudent(studentID))return "Student approved";
    	return "Operation Failed...";
    	//student.setApproved(true);
    }

	@Override
	public String viewCourses() {
		// TODO Auto-generated method stub
		String catalog="";
		Set<Course> courses=adi.viewCourses();
		for (Course course : courses) {
	        String prof = course.getCourseProf();
	        //System.out.println(course.getCourseID() + " " + course.getCourseName());
	        if (prof == null) prof = "Prof Awaited";
	        catalog = catalog.concat(course.getCourseID() + " " + course.getCourseName() + " " + prof + " " + String.valueOf(course.getSeats()) + "\n");
	    }//System.out.println(catalog);
		return catalog;
	}
	
	@Override
	public String viewProfessors() {
		// TODO Auto-generated method stub
		String catalog="";
		Set<Prof> profs=adi.viewProfessors();
		for (Prof prof : profs) {
	        catalog = catalog.concat(prof.getName() + " " + prof.getID() + " " + prof.getDept() + "\n");
	    }//System.out.println(catalog);
		return catalog;
	}

	@Override
	public String viewUnapprovedStudents() {
		// TODO Auto-generated method stub
		Set<Student> studentList=adi.viewUnapprovedStudents();
    	String students="";
    	for(Student student:studentList) {
    		students=students.concat(student.getID()+":"+student.getName()+":"+student.getRollNum());
    	}return students;
	}
}
