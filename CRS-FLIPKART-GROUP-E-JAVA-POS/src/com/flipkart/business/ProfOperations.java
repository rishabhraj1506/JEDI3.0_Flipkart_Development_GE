package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.ProfDaoInterface;
import com.flipkart.dao.ProfDaoServices;
import com.flipkart.exception.CourseNotAvailableException;
import com.flipkart.exception.CourseNotOfferedException;
import com.flipkart.exception.CourseNotOptedException;
import com.flipkart.exception.GradeAlreadyAddedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class ProfOperations implements ProfInterface{
	ProfDaoInterface pdi = new ProfDaoServices();
    /**
     * Method to offer a course
     * @param course: the course to be offered
     */
    public String offerCourse(String courseID, Prof prof) {
        try {
			if(pdi.offerCourse(courseID, prof))return "Course enrolled successfully";
		} catch (CourseNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Enrollment failed...";
        //
    }

    /**
     * Method to get the registered students for a given course
     * @param course: the course for which to get the registered students
     * @return set of registered students
     */
    public String getStudents(String courseID, Prof prof) {
    	//return prof.getRegisteredStudents(courseID);
    	Set<Student> studentList=pdi.getStudents(courseID, prof);
    	String students="";
    	for(Student student:studentList) {
    		students=students.concat(student.getID()+":"+student.getName()+":"+student.getRollNum());
    	}return students;
    }

    /**
     * Method to give a grade to a student in a course
     * @param course: the course in which the grade is to be given
     * @param student: the student to whom the grade is to be given
     * @param grade: the grade to be given
     * @return true if grade was successfully assigned, false otherwise
     */
    public String giveGrade(String courseID, String studentID, String grade, Prof prof) {
         // Student is not registered for the course
    	//Set<Student> studentList=prof.getRegisteredStudents(courseID);
    	
    	/*for(Student stu:studentList) {
    		if(stu.getID().equals(studentID)) {
    			stu.updateGrade(courseID, grade);
    			return true;
    		}
    	}*/
    	try {
			if(pdi.giveGrade(courseID, studentID, grade, prof))return "Grade submitted successfully";
		} catch (CourseNotOptedException | GradeAlreadyAddedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseNotOfferedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "Grade submission failed...";
    }
    
    @Override
	public String viewCourses() {
		// TODO Auto-generated method stub
		String catalog="";
		Set<Course> courses=pdi.viewCourses();
		for(Course course:courses) {
			String prof=course.getCourseProf();
			if(prof==null)prof="Prof Awaited";
			catalog=catalog.concat(course.getCourseID()+" "+course.getCourseName()+" "+prof+" "+course.getSeats()+"\n");
		}
		return catalog;
	}

	@Override
	public String viewCourseOffering(Prof prof) {
		// TODO Auto-generated method stub
		String catalog="";
		Set<Course> courses=pdi.viewCourseOffering(prof);
		for(Course course:courses) {
			catalog=catalog.concat(course.getCourseID()+" "+course.getCourseName()+"\n");
		}
		return catalog;
	}
}
