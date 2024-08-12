package com.flipkart.dao;

import java.util.List;
import java.util.Set;

import com.flipkart.bean.Billing;
import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

public interface StudentDaoInterface {
	public float register(Student student, String coursesID);
	
	public List<Course> viewCoursesEnrolled(Student student);
	
	public Set<Course> viewCourses();
	
	public ReportCard getReport(Student student);
	
	public Billing getBillingInfo(Student student);

	boolean updateBillingInfo(Billing billing);
}
