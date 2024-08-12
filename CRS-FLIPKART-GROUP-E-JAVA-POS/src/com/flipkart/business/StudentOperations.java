package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoServices;
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
			float temp=sdi.register(student, courseID);
			if(temp>0) {
				price+=temp;
				count++;
				confirmedRegistration=confirmedRegistration.concat(courseID+"\n");
			}
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
        Billing billing = sdi.getBillingInfo(student);
        String status = "Pending";
        if (billing.isStatus()) status = "Completed";
        return billing.getBillingID() + ":" + billing.getBillamt() + ":" + status;
    }

	/**
	 * Method to make a payment for the student
	 * @param student: Student object
	 * @param amount: amount to be paid
	 * @param paymentType: type of payment (e.g., CreditCard, NetBanking)
	 * @return confirmation message
	 */
	public String makePayment(Student student, float amount, String paymentType) {
	    // Retrieve billing information using the Student object
	    Billing billing = sdi.getBillingInfo(student);
	    
	    if (billing == null) {
	        return "Billing information not found for student ID: " + student.getID();
	    }

	    if (billing.isStatus()) {
	        return "Payment already completed for billing ID: " + billing.getBillingID();
	    }

	    // Generate a unique transaction ID
	    String transactionID = "TXN" + System.currentTimeMillis();
	    billing.setTransactionID(transactionID);
	    billing.setBillamt(amount);
	    billing.setStatus(true);

	    // Update billing information in the database
	    boolean paymentSuccess = sdi.updateBillingInfo(billing);
	    
	    if (paymentSuccess) {
	        return "Payment Successful. Transaction ID: " + transactionID;
	    } else {
	        return "Payment failed. Please try again.";
	    }
	}


	@Override
	public int getValidCount(List<String> courses) {
		
		return 0;
	}

	@Override
	public String viewCourses() {
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
}
