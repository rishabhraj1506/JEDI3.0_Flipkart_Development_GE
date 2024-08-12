package com.flipkart.dao;

import java.util.List;
import java.util.Set;

import com.flipkart.bean.Billing;
import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.exception.BillingNotFoundException;
import com.flipkart.exception.CourseAlreadyOptedException;
import com.flipkart.exception.CourseNotAvailableException;
import com.flipkart.exception.CourseNotFoundException;

public interface StudentDaoInterface {
	public float register(Student student, String coursesID) throws CourseAlreadyOptedException, CourseNotAvailableException, CourseNotFoundException;
	
	public List<Course> viewCoursesEnrolled(Student student);
	
	public Set<Course> viewCourses();
	
	public ReportCard getReport(Student student);
	
	public Billing getBillingInfo(Student student) throws BillingNotFoundException;
	
	boolean updateBillingInfo(Billing billing);
}
