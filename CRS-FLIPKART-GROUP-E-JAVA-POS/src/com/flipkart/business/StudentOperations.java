package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoServices;
import com.flipkart.exception.BillingNotFoundException;
import com.flipkart.exception.CourseAlreadyOptedException;
import com.flipkart.exception.CourseNotAvailableException;
import com.flipkart.exception.CourseNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentOperations implements StudentInterface {
	StudentDaoInterface sdi=new StudentDaoServices();
    /**
     * Method to register the student in a course
     * @param course: the course to register
     * @return true if registration was successful, false otherwise
     */
	public String register(Student student, List<String> courses) {
		String confirmedRegistration="";
		int count=0;
		float price=0;
		for(String courseID:courses) {
			if(count==4)break;
			float temp=0;
			try {
				temp = sdi.register(student, courseID);
			} catch (CourseAlreadyOptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseNotAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			confirmedRegistration=confirmedRegistration.concat(courseID+"\n");
			price+=temp;
			
		}
		return confirmedRegistration.concat("price: " + String.valueOf(price));
	}
    
    /**
     * Method to view courses registered by the student
     * @return list of registered courses
     */
	
    public String viewCoursesEnrolled(Student student) {
        //return student.courseList();
    	String courses="";
    	List<Course> courseList=sdi.viewCoursesEnrolled(student);
    	for(Course course:courseList) {
    		courses=courses.concat(course.getCourseID()+":"+course.getCourseName()+":"+course.getCourseProf()+"\n");
    	}
    	return courses;
    }
    
    /**
     * Method to get a report of registered courses
     * @return a string report of registered courses
     */
    public String getReport(Student student) {
        //return student.getReport();
    	String report="";
    	ReportCard reportCard=sdi.getReport(student);
    	for (Map.Entry<String, String> entry : reportCard.getGrades().entrySet()) {
            report=report.concat(entry.getKey()+ ":" + entry.getValue());
        }
    	return report;
    }
    
    /**
     * Method to get billing information
     * @return billing information
     */
    public String getBillingInfo(Student student) {
        //return student.getBilling().infoAboutPay();
    	Billing billing;
		try {
			billing = sdi.getBillingInfo(student);
	    	String status="Pending";
	    	if(billing.isStatus())status="Completed";
	    	return billing.getBillingID()+":"+billing.getBillamt()+":"+status;
		} catch (BillingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

	@Override
	public int getValidCount(List<String> courses) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String viewCourses() {
		// TODO Auto-generated method stub
		String catalog="";
		Set<Course> courses=sdi.viewCourses();
		for (Course course : courses) {
	        String prof = course.getCourseProf();
	        //System.out.println(course.getCourseID() + " " + course.getCourseName());
	        if (prof == null) prof = "Prof Awaited";
	        catalog = catalog.concat(course.getCourseID() + " " + course.getCourseName() + " " + prof + " " + String.valueOf(course.getSeats()) + "\n");
	    }//System.out.println(catalog);
		return catalog;
	}
	
	@Override
	public String makePayment(Student student, float amount, String transactionID) {
	    // Retrieve billing information using the Student object
	    Billing billing;
		try {
			billing = sdi.getBillingInfo(student);
			if (billing.isStatus()) {
		        return "Payment already completed for billing ID: " + billing.getBillingID();
		    }

		    // Generate a unique transaction ID
		    billing.setTransactionID(transactionID);
		    billing.setBillamt(amount);

		    // Update billing information in the database
		    boolean paymentSuccess = sdi.updateBillingInfo(billing);
		    
		    if (paymentSuccess) {
		        return "Payment Successful. Transaction ID: " + transactionID;
		    } else {
		        return "Payment failed. Please try again.";
		    }
		} catch (BillingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	    
	}

	@Override
	public float getCoursePricing(Student student) {
		// TODO Auto-generated method stub
		float price=0;
    	List<Course> courseList=sdi.viewCoursesEnrolled(student);
    	for(Course course:courseList) {
    		price+=course.getPrice();
    	}
    	return price;
	}
}
