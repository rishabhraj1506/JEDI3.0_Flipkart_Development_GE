package com.flipkart.bean;

import java.util.HashSet;
import java.util.Set;


public class Course {

	private String courseID;
	private String courseName;
	Prof courseProf;
	private int seats;
	private Set<Student> enrolledStudents; // To keep track of enrolled students
	
	/**
	 * Default Constructor
	 */
	public Course() {
		this.enrolledStudents = new HashSet<>();
	}
	
	/**
	 * Parameterized constructor
	 * @param courseID: course ID
	 * @param courseName: course name
	 * @param courseProf: professor user id
	 * @param seats: seats available 
	 */
	public Course(String courseID, String courseName, Prof courseProf, int seats) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseProf = courseProf;
		this.seats = seats;
		this.enrolledStudents = new HashSet<>();
	}
	
	/**
	 * Method to get Course ID
	 * @return Course ID
	 */
	public String getCourseID() {
		return courseID;
	}
	
	/**
	 * Method to set Course ID
	 * @param courseID
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	/**
	 * Method to get Course Name
	 * @return Course Name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Method to set Course Name
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Method to get available seats
	 * @return Seats available
	 */
	public int getSeats() {
		return seats;
	}
	
	/**
	 * Method to set available seats
	 * @param seats
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	/**
	 * Method to get Professor ID of the professor teaching the course
	 * @return Professor ID
	 */
	public Prof getCourseProf() {
		return courseProf;
	}
	
	/**
	 * Method to set Professor ID of the professor teaching the course
	 * @param courseProf
	 */
	public void setCourseProf(Prof courseProf) {
		this.courseProf = courseProf;
	}
	
	public boolean isCourseFull() {
		return enrolledStudents.size() >= seats;
	}
	
	/**
	 * Method to add a student to the course
	 * @param student: the student to be added
	 * @return true if the student was added successfully, false if the course is full
	 */
	public boolean addStudent(Student student) {
		if (!isCourseFull()) {
			return enrolledStudents.add(student);
		}
		return false; // Course is full
	}
	
	/**
	 * Method to determine if the course can be canceled
	 * @return true if the course can be canceled, false otherwise
	 */
	public boolean canCancel() {
		// Example criteria for cancellation: if the course has no students enrolled, it can be canceled
		return enrolledStudents.isEmpty();
	}
	
	public Set<Student> getStudents(){
		return enrolledStudents;
	}
}
